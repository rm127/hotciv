package hotciv.common;

import hotciv.framework.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

  private final GameAgingStrategy gameAgingStrategy;
  private final GameWinStrategy gameWinStrategy;
  private final UnitActionStrategy unitActionStrategy;
  private final BattleStrategy battleStrategy;
  private final UnitStatStrategy unitStatStrategy;
  private final TileValidatorStrategy tileValidatorStrategy;

  GameImpl(GameFactory gameFactory) {
    this.gameAgingStrategy = gameFactory.createGameAgingStrategy();
    this.gameWinStrategy = gameFactory.createGameWinStrategy();
    this.unitActionStrategy = gameFactory.createUnitActionStrategy();
    this.battleStrategy = gameFactory.createBattleStrategy();
    this.unitStatStrategy = gameFactory.createUnitStatStrategy();
    this.tileValidatorStrategy = gameFactory.createTileValidatorStrategy();

    WorldLayoutStrategy worldLayoutStrategy = gameFactory.createWorldLayoutStrategy(this);

    tileMap = worldLayoutStrategy.getTileMap();
    unitMap = new HashMap<>();
    cityMap = new HashMap<>();

    worldLayoutStrategy.createCities();
    worldLayoutStrategy.createUnits();
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
    return gameWinStrategy.getWinner(this);
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
      gameWinStrategy.incrementBattleWon(currentPlayer);
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
    if (isInvalidTile(getTileAt(to).getTypeString(), unit.getTypeString())) return false;

    // trying to move more than moveCount allows
    boolean unitHasEnoughMovesLeft = unit.getMoveCount() >= distanceBetween(from, to);
    if (!unitHasEnoughMovesLeft) return false;

    // trying to move when unit is fortified
    if (((UnitImpl) unit).isFortified()) return false;
    return true;
  }

  // validate if tile is ocean or mountain
  private boolean isInvalidTile(String tileType, String unitType) {
    return !tileValidatorStrategy.canMoveHere(tileType, unitType);
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
    gameWinStrategy.incrementRoundNumber();
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

      Position newPosition = nextValidUnitPosition(position, city.getProduction());
      // to avoid NullPointerException
      if (newPosition != null) {
        unitMap.put(newPosition, new UnitImpl(unitStatStrategy, currentPlayer, city.getProduction()));
        ((CityImpl) city).decreaseTreasury();
      }
    }
  }

  private Position nextValidUnitPosition(Position position, String unitType) {
    if (getUnitAt(position) == null) {
      return position;
    }
    Iterator<Position> adjacentPositions = Utilities.getAdjacentPositions(position);
    while (adjacentPositions.hasNext()) {
      Position newPosition = adjacentPositions.next();
      if (getUnitAt(newPosition) == null && !isInvalidTile(getTileAt(newPosition).getTypeString(), unitType)) {
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
    // If the unit is not owned by the current player
    if (getPlayerInTurn() != getUnitAt(p).getOwner()) return;
    unitActionStrategy.performAction(p, this);
  }

  public void addCityAt(Position p, Player owner) {
    cityMap.put(p, new CityImpl(unitStatStrategy, owner));
  }

  public void addUnitAt(Position position, Player owner, String unitType) {
    unitMap.put(position, new UnitImpl(unitStatStrategy, owner, unitType));
  }

  public void removeUnitAt(Position p) {
    unitMap.remove(p);
  }

  public Map<Position, City> getCities() {
    return cityMap;
  }
}
