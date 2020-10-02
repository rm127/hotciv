package hotciv.common;

import hotciv.framework.*;
import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {
  private Player currentPlayer = Player.RED; // red always starts
  private int gameAge = -4000; // game starts at 4000 BC
  private final HashMap<Position, City> cityMap;
  private final HashMap<Position, Unit> unitMap;
  private final HashMap<Position, Tile> tileMap;
  private final HashMap<Player, Integer> playerBattleStats;

  private final GameAgingStrategy gameAgingStrategy;
  private final GameWinStrategy gameWinStrategy;
  private final UnitActionStrategy unitActionStrategy;

  GameImpl(GameAgingStrategy gameAgingStrategy, GameWinStrategy gameWinStrategy, UnitActionStrategy unitActionStrategy, WorldLayoutStrategy worldLayoutStrategy) {
    this.gameAgingStrategy = gameAgingStrategy;
    this.gameWinStrategy = gameWinStrategy;
    this.unitActionStrategy = unitActionStrategy;
    // create battle stats map and add the two players as 0
    playerBattleStats = new HashMap<>();
    playerBattleStats.put(Player.RED, 0);
    playerBattleStats.put(Player.BLUE, 0);

    cityMap = worldLayoutStrategy.getCityMap();
    unitMap = worldLayoutStrategy.getUnitMap();
    tileMap = worldLayoutStrategy.getTileMap();
  }

  public Tile getTileAt( Position p ) {
    return tileMap.get(p);
  }

  public Unit getUnitAt( Position p ) {
    return unitMap.get(p);
  }

  public City getCityAt( Position p ) {
      return cityMap.get(p);
  }

  public Player getPlayerInTurn() {
    return currentPlayer;
  }

  public Player getWinner() {
    return gameWinStrategy.getWinner(gameAge, cityMap, playerBattleStats);
  }

  public int getAge() {
    return gameAge;
  }

  public boolean moveUnit(Position from, Position to) {
    Unit unit = this.getUnitAt(from);

    // is valid move
    if (!isValidMove(from, to)) return false;

    // kills another unit
    boolean destinationHasEnemyUnit = this.getUnitAt(to) != null;
    if (destinationHasEnemyUnit) {
      // increase won battles counter
      playerBattleStats.put(currentPlayer, playerBattleStats.get(currentPlayer)+1);
      // remove killed unit
      unitMap.remove(to);
    }

    // takes over city
    City destinationCity = this.getCityAt(to);
    boolean destinationHasEnemyCity = destinationCity != null && destinationCity.getOwner() != currentPlayer;
    if (destinationHasEnemyCity) ((CityImpl) destinationCity).changeOwner(currentPlayer);

    unitMap.put(to, unit);
    unitMap.remove(from);
    // decrease move count
    ((UnitImpl) unit).decreaseMoveCount();
    return true;
  }

  private boolean isValidMove(Position from, Position to) {
    Unit unit = this.getUnitAt(from);
    // trying to move a unit that's not yours
    boolean isCurrentPlayersUnit = unit.getOwner() == currentPlayer;
    if (!isCurrentPlayersUnit) return false;

    // trying to stack your units
    boolean destinationHasUnitOfCurrentPlayer = getUnitAt(to) != null && getUnitAt(to).getOwner() == unit.getOwner();
    if (destinationHasUnitOfCurrentPlayer) return false;

    // trying to move to invalid tile (ocean or mountain)
    if (isInvalidTile(to)) return false;

    // trying to move more than moveCount allows
    boolean unitHasEnoughMovesLeft = unit.getMoveCount() >= distanceBetween(from, to);
    if (!unitHasEnoughMovesLeft) return false;

    // trying to move when unit is fortified
    if (((UnitImpl) unit).isFortified()) return false;
    return true;
  }

  // validate if tile is ocean or mountain
  private boolean isInvalidTile(Position position) {
    String destinationTileType = this.getTileAt(position).getTypeString();
    return destinationTileType.equals(OCEANS) || destinationTileType.equals(MOUNTAINS);
  }

  private int distanceBetween(Position from, Position to) {
    int horizontalDiff = Math.abs(to.getColumn() - from.getColumn());
    int verticalDiff = Math.abs(to.getRow() - from.getRow());
    if (horizontalDiff == verticalDiff) {
      return horizontalDiff;
    } else {
      return horizontalDiff + verticalDiff;
    }
  }

  public void endOfTurn() {
    // alternate between players' turns
    if (currentPlayer == Player.RED) {
      currentPlayer = Player.BLUE;
    } else {
      currentPlayer = Player.RED;
      this.endOfRound();
    }
  }

  private void endOfRound() {
    // increase the age of the game
    gameAge += gameAgingStrategy.calculateAgeIncrease(gameAge);
    // update production in cities
    cityMap.forEach(this::handleCityProduction);
    // reset move count
    unitMap.forEach((position, unit) -> ((UnitImpl) unit).resetMoveCount());
  }

  private void handleCityProduction(Position position, City city) {
    ((CityImpl) city).increaseTreasury();
    if (city.getTreasury() >= ((CityImpl) city).getProductionPrice()) {

      Position newPosition = nextValidUnitPosition(position);
      // to avoid NullPointerException
      if (newPosition != null) {
        unitMap.put(newPosition, new UnitImpl(currentPlayer, city.getProduction()));
        ((CityImpl) city).decreaseTreasury();
      }
    }
  }

  private Position nextValidUnitPosition(Position position) {
    int currentRow = position.getRow();
    int currentColumn = position.getColumn();
    // list of modifiers to current position
    int[] columns = new int[] {0, 0, 1, 1, 1, 0, -1, -1, -1};
    int[] rows    = new int[] {0, -1, -1, 0, 1, 1, 1, 0, -1};

    for (int i = 0; i < 9; i++) {
      Position newPosition = new Position(currentRow + rows[i], currentColumn + columns[i]);
      if (getUnitAt(newPosition) == null && !isInvalidTile(newPosition)) {
        return newPosition;
      }
    }
    return null;
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

  public void changeProductionInCityAt( Position p, String unitType ) {
    ((CityImpl) this.getCityAt(p)).setProduction(unitType);
  }

  public void performUnitActionAt( Position p ) {
    unitActionStrategy.performAction(p, this);
  }

  public void addCityAt(Position p, City city) {
    cityMap.put(p, city);
  }

  public void removeUnitAt(Position p) {
    unitMap.remove(p);
  }
}
