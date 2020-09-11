package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.ARCHER;
import static hotciv.framework.GameConstants.SETTLER;

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
  private final HashMap<Position, City> cityMap = new HashMap<>();
  private final HashMap<Position, Unit> unitMap = new HashMap<>();


  GameImpl() {
    // cities
    cityMap.put(new Position(1,1), new CityImpl(Player.RED));
    cityMap.put(new Position(4,1), new CityImpl(Player.BLUE));

    // units
    unitMap.put(new Position(2,0), new UnitImpl(Player.RED, ARCHER));
    unitMap.put(new Position(3,2), new UnitImpl(Player.BLUE, ARCHER));
    unitMap.put(new Position(4,3), new UnitImpl(Player.RED, SETTLER));
  }

  public Tile getTileAt( Position p ) { return null; }
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
    return Player.RED;
  }

  public int getAge() {
    return gameAge;
  }

  public boolean moveUnit(Position from, Position to) {
    Unit unit = this.getUnitAt(from);

    // trying to move a unit that's not yours
    if (unit.getOwner() != currentPlayer) {
      // possibly throw InvalidMoveException at later stage to differentiate between errors?
      return false;
    }

    // trying to stack your units
    if (getUnitAt(to) != null && getUnitAt(to).getOwner() == unit.getOwner()) {
      return false;
    }

    // trying to move more than moveCount allows
    if (unit.getMoveCount() < distanceBetween(from, to)) {
      return false;
    }

    unitMap.put(to, unit);
    unitMap.remove(from);
    // decrease move count
    ((UnitImpl) unit).decreaseMoveCount();
    return true;
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
      // increase the age of the game
      gameAge += 100;
      // update production in cities
      cityMap.forEach((position, city) -> ((CityImpl) city).increaseTreasury());
      // reset move count
      unitMap.forEach((position, unit) -> ((UnitImpl) unit).resetMoveCount());
    }
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

  public void changeProductionInCityAt( Position p, String unitType ) {
    ((CityImpl) this.getCityAt(p)).setProduction(unitType);
  }

  public void performUnitActionAt( Position p ) {}
}
