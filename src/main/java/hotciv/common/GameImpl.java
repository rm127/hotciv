package hotciv.common;

import hotciv.framework.*;

import java.util.HashMap;
import java.util.Iterator;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.GameConstants.FOREST;

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
  private final BattleStrategy battleStrategy;

  GameImpl(GameAgingStrategy gameAgingStrategy, GameWinStrategy gameWinStrategy, UnitActionStrategy unitActionStrategy, WorldLayoutStrategy worldLayoutStrategy, BattleStrategy battleStrategy) {
    this.gameAgingStrategy = gameAgingStrategy;
    this.gameWinStrategy = gameWinStrategy;
    this.unitActionStrategy = unitActionStrategy;
    this.battleStrategy = battleStrategy;
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
      return battleStrategy.executeBattle(this, from, to);
    }

    // takes over city
    City destinationCity = this.getCityAt(to);
    boolean destinationHasEnemyCity = destinationCity != null && destinationCity.getOwner() != currentPlayer;
    if (destinationHasEnemyCity) ((CityImpl) destinationCity).changeOwner(currentPlayer);

    unitMap.put(to, unit);
    this.removeUnitAt(from);
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
    if (getUnitAt(position) == null) {
      return position;
    }
    Iterator<Position> adjacentPositions = Utilities.getAdjacentPositions(position);
    while (adjacentPositions.hasNext()) {
      Position newPosition = adjacentPositions.next();
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

  public int calculateAttackingStrength(Position p) {
    return calculateUnitStrength(p, getUnitAt(p).getAttackingStrength());
  }
  public int calculateDefensiveStrength(Position p) {
    return calculateUnitStrength(p, getUnitAt(p).getDefensiveStrength());
  }


  private int calculateUnitStrength(Position p, int unitStrength) {
    int terrainFactor = 1;
    if (getCityAt(p) != null) {
      terrainFactor = 3;
    }
    String tileType = getTileAt(p).getTypeString();
    if (tileType.equals(HILLS) || tileType.equals(FOREST)) {
      terrainFactor = 2;
    }

    int adjacentUnitSupport = 0;
    Iterator<Position> adjacentPositions = Utilities.getAdjacentPositions(p);
    while (adjacentPositions.hasNext()) {
      Position position = adjacentPositions.next();
      Unit unit = getUnitAt(position);
      // if a unit exists and belongs to the same owner as the unit in question
      if (unit != null && unit.getOwner() == getUnitAt(p).getOwner()) {
        adjacentUnitSupport++;
      }
    }
    return (unitStrength + adjacentUnitSupport) * terrainFactor;
  }
}
