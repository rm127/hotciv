package hotciv.common;

import hotciv.framework.*;

import hotciv.variants.*;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/** Skeleton class for AlphaCiv test cases

   Updated Aug 2020 for JUnit 5 includes
   Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
  private Game game;

  /**
   * Fixture for AlphaCiv testing.
   */
  @BeforeEach
  public void setUp() {
    game = new GameImpl(new AlphaGameFactory());
  }

  // Red player starts the game
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  // After the player red has taken their turn, it shall be player blue's turn
  @Test
  public void shouldBePlayerBlueAfterPlayerRed() {
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  // The two players (red and blue) alternates taking turns
  @Test
  public void playersShouldAlternateTakingTurns() {
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  // The game starts at age 4000 BC
  @Test
  public void gameStartsAt4000BC() {
    assertThat(game.getAge(), is(-4000));
  }

  // Game age increases at every round end
  @Test
  public void gameAgeIncreasesAtEveryRoundEnd() {
    int startAge = game.getAge();
    // advance a round
    skipOtherPlayersTurn();
    assertThat(game.getAge(), is(startAge + 100));
    int newAge = game.getAge();
    // advance another round
    skipOtherPlayersTurn();
    assertThat(game.getAge(), is(newAge + 100));
  }

  // Red wins at year 3000 BC and winner is not found until game is over
  @Test
  public void redWinsAtYear3000BC() {
    while (game.getAge() < -3000) {
      assertThat(game.getWinner(), is(nullValue()));
      skipOtherPlayersTurn();
    }
    assertThat(game.getWinner(), is(Player.RED));
  }

  // Red has a city at position (1, 1)
  @Test
  public void redHasACityAtPosition11() {
    City city = game.getCityAt(new Position(1, 1));
    assertThat(city, is(notNullValue()));
    assertThat(city.getOwner(), is(Player.RED));
  }

  // Blue has a city at position (4, 1)
  @Test
  public void blueHasACityAtPosition14() {
    City city = game.getCityAt(new Position(4, 1));
    assertThat(city, is(notNullValue()));
    assertThat(city.getOwner(), is(Player.BLUE));
  }

  // Cities have population of 1
  @Test
  public void citiesHavePopulation1() {
    City city = game.getCityAt(new Position(4, 1));
    assertThat(city.getSize(), is(1));
  }

  // A city starts with 0 production in the treasury
  @Test
  public void cityStartsWith0Production() {
    City city = game.getCityAt(new Position(1,1));
    assertThat(city.getTreasury(), is(0));
  }

  // A city gets 6 production each round
  @Test
  public void citiesGet6ProductionPerRound() {
    City city = game.getCityAt(new Position(1,1));
    int startTreasury = city.getTreasury();
    game.endOfTurn();
    game.endOfTurn();
    assertThat(city.getTreasury(), is(startTreasury + 6));
  }

  // At game start A Red Archer is at (2,0)
  @Test
  public void redHasArcherAtPosition() {
    Unit unit1 = game.getUnitAt(new Position(2,0));
    assertThat(unit1, is(notNullValue()));
    assertThat(unit1.getTypeString(), is(ARCHER));
    assertThat(unit1.getOwner(), is(Player.RED));
  }

  // At game start A Blue Legion is at (3,2)
  @Test
  public void blueHasArcherAtPosition() {
    Unit unit1 = game.getUnitAt(new Position(3,2));
    assertThat(unit1, is(notNullValue()));
    assertThat(unit1.getTypeString(), is(ARCHER));
    assertThat(unit1.getOwner(), is(Player.BLUE));
  }

  // At game start A Red Settler is at (4,3)
  @Test
  public void redHasSettlerAtPosition() {
    Unit unit1 = game.getUnitAt(new Position(4,3));
    assertThat(unit1, is(notNullValue()));
    assertThat(unit1.getTypeString(), is(SETTLER));
    assertThat(unit1.getOwner(), is(Player.RED));
  }

  // All units can move a maximum of 1 in distance
  @Test
  public void unitsHaveMaxMovingDistanceOf1() {
    Unit unit = game.getUnitAt(new Position(2,0));
    assertThat(unit.getMoveCount(), is(1));
  }

  // A city must always be producing something
  @Test
  public void cityShouldAlwaysBeProducingSomething() {
    City city = game.getCityAt(new Position(1,1));
    String currentlyProducing = city.getProduction();
    assertThat(currentlyProducing, is(notNullValue()));
    assertThat(currentlyProducing, anyOf(is(ARCHER), is(SETTLER), is(LEGION)));
  }

  // It is possible to change the production of a city
  @Test
  public void possibleToChangeProductionInCity() {
    Position cityPosition = new Position(1,1);
    City city = game.getCityAt(cityPosition);
    game.changeProductionInCityAt(cityPosition, LEGION);
    game.changeProductionInCityAt(cityPosition, SETTLER);
    assertThat(city.getProduction(), is(SETTLER));
  }

  // A city should be the same one (ref) between different turns (storing cities after creation)
  @Test
  public void sameCityShouldBeTheSame() {
    City city1 = game.getCityAt(new Position(1,1));
    City city2 = game.getCityAt(new Position(1,1));
    assertThat(city1, is(city2));
  }

  // A unit should be the same one (ref) between different turns (storing units after creation)
  @Test
  public void sameUnitShouldBeTheSame() {
    Unit unit1 = game.getUnitAt(new Position(2,0));
    Unit unit2 = game.getUnitAt(new Position(2,0));
    assertThat(unit1, is(unit2));
  }

  // Units can be moved
  @Test
  public void unitsCanBeMoved() {
    // unit owned by red. Red starts the game
    Position oldPosition = new Position(2,0);
    Position newPosition = new Position(2,1);
    Unit unit = game.getUnitAt(oldPosition);
    game.moveUnit(oldPosition, newPosition);
    assertThat(game.getUnitAt(newPosition), is(unit));
    assertThat(game.getUnitAt(oldPosition), is(nullValue()));
  }

// Red cannot move Blue's units
  @Test
  public void redCannotMoveBlueUnits() {
    // unit at position (2,3) is owned by blue
    boolean invalidMove = game.moveUnit(new Position(3,2), new Position(3,3));
    assertThat(invalidMove, is(false));
  }

  // Cannot stack two units with same owner at the same position
  @Test
  public void cannotStackUnitsOfSameOwner() {
    /*
     * Info: both units are red
     * Setup: Move a red unit close to another red unit and make the test
     */

    // move down past blue city
    game.moveUnit(new Position(2,0), new Position(3,0));
    skipOtherPlayersTurn();
    game.moveUnit(new Position(3,0), new Position(4,0));
    skipOtherPlayersTurn();
    game.moveUnit(new Position(4,0), new Position(5,0));
    skipOtherPlayersTurn();
    // move across
    game.moveUnit(new Position(5,0), new Position(5,1));
    skipOtherPlayersTurn();
    game.moveUnit(new Position(5,1), new Position(5,2));
    skipOtherPlayersTurn();
    game.moveUnit(new Position(5,2), new Position(5,3));
    skipOtherPlayersTurn();
    // move up
    boolean invalidMove = game.moveUnit(new Position(5,3), new Position(4,3));
    assertThat(invalidMove, is(false));
  }

  // Unit can only be moved a maximum of tiles equal moveCount
  @Test
  void unitOnlyAllowedToMoveAMaxOfMoveCount() {
    int startColumn = 3;
    int row = 4;
    Position startPosition = new Position(row,startColumn);
    int moveCount = game.getUnitAt(startPosition).getMoveCount();
    boolean invalidMove = game.moveUnit(startPosition, new Position(row,startColumn + moveCount + 1));
    assertThat(invalidMove, is(false));
    boolean validMove = game.moveUnit(startPosition, new Position(row,startColumn + moveCount));
    assertThat(validMove, is(true));
  }

  // Moving a unit diagonally reduces moveCount by correct amount
  @Test
  void movingUnitDiagonallyReducesMoveCountByCorrectAmount() {
    Unit unit = game.getUnitAt(new Position(4,3));
    int prevMoveCount = unit.getMoveCount();
    game.moveUnit(new Position(4,3), new Position(5,4));
    assertThat(unit.getMoveCount(), is(prevMoveCount - 1));
  }

  // Moving a unit decreases it's moveCount by the distance moved
  @Test
  void movingUnitDecreasesMoveCountByDistanceMoved() {
    Position unitPosition = new Position(4,3);
    Unit unit = game.getUnitAt(unitPosition);
    int origMoveCount = unit.getMoveCount();
    game.moveUnit(unitPosition, new Position(4,4));
    assertThat(unit.getMoveCount(), is(origMoveCount - 1));
  }

  // MoveCount is reset every round
  @Test
  void moveCountIsResetEachRound() {
    Position unitPosition = new Position(4,3);
    Unit unit = game.getUnitAt(unitPosition);
    int moveCount = unit.getMoveCount();
    skipOtherPlayersTurn();
    assertThat(moveCount, is(unit.getMoveCount()));
  }

  // Tile (2,2) is Mountains
  @Test
  void Tile22IsMountains() {
    Tile tile = game.getTileAt(new Position(2,2));
    assertThat(tile.getTypeString(), is(MOUNTAINS));
  }
  // All Tiles are default Planes
  @Test
  void AllTilesAreDefaultPlanes() {
    Tile tile = game.getTileAt(new Position(0,0));
    assertThat(tile.getTypeString(), is(PLAINS));
    tile = game.getTileAt(new Position(1,1));
    assertThat(tile.getTypeString(), is(PLAINS));
    tile = game.getTileAt(new Position(3,3));
    assertThat(tile.getTypeString(), is(PLAINS));
  }

  // Tile (1,0) is Ocean
  @Test
  void Tile10IsOcean() {
    Tile tile = game.getTileAt(new Position(1,0));
    assertThat(tile.getTypeString(), is(OCEANS));
  }

  // Tile (0,1) is Hills
  @Test
  void Tile01IsHills() {
    Tile tile = game.getTileAt(new Position(0,1));
    assertThat(tile.getTypeString(), is(HILLS));
  }

  // Unit cannot be moved to a mountain tile
  @Test
  void movingUnitToMountainIsNotPossible() {
    // change turn to player blue to move blue unit at pos (2,3)
    game.endOfTurn();
    assertThat(game.moveUnit(new Position(3, 2), new Position(2, 2)), is(false));
  }

  // Unit cannot be moved to an ocean tile
  @Test
  void movingUnitToOceanIsNotPossible() {
    assertThat(game.moveUnit(new Position(2, 0), new Position(1, 0)), is(false));
  }

  // Moving unit A onto tile of unit B kills unit
  @Test
  void movingUnitAOntoTileOfUnitBKillsUnitB() {
    Position unitAPosition = new Position(4,3);
    Position unitBPosition = new Position(3,2);
    Unit attackingUnit = game.getUnitAt(unitAPosition);
    game.moveUnit(unitAPosition, unitBPosition);
    assertThat(game.getUnitAt(unitBPosition), is(attackingUnit));
  }

  // Moving a unit to a city owned by an opponent will change the city's ownership
  @Test
  void movingUnitToOpponentCityWillConvertCityToCurrentPlayer() {
    Position cityPosition = new Position(4,1);
    // move unit since we can only move one tile a time
    game.moveUnit(new Position(2, 0), new Position(3,0));
    skipOtherPlayersTurn();

    game.moveUnit(new Position(3, 0), cityPosition);
    assertThat(game.getCityAt(cityPosition).getOwner(), is(Player.RED));
  }

  // A city's workforce balance can only be set to production
  @Test
  void cityWorkforceFocusShouldBeProduction() {
    assertThat(game.getCityAt(new Position(1,1)).getWorkforceFocus(), is(productionFocus));
  }

  // When having enough production to produce a unit, the unit is produced
  @Test
  void shouldProduceUnitWhenTreasuryIsHighEnough() {
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    assertThat(game.getUnitAt(new Position(1, 1)).getTypeString(), is(LEGION));
  }

  // The correct unit type is produced
  @Test
  void correctUnitTypeIsProducedFromCity() {
    City city = game.getCityAt(new Position(1, 1));
    ((CityImpl) city).setProduction(SETTLER);
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    assertThat(game.getUnitAt(new Position(1, 1)).getTypeString(), is(SETTLER));
  }

  // After producing a unit, the treasury will be reduced by the cost of the unit
  @Test
  void treasuryIsReducedByLegionCostAfterLegionProduction() {
    City city = game.getCityAt(new Position(1, 1));
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    // Treasury is increased by 6 each round. Legion costs 15
    assertThat(city.getTreasury(), is(3));
  }

  @Test
  void treasuryIsReducedByArcherCostAfterArcherProduction() {
    City city = game.getCityAt(new Position(1, 1));
    ((CityImpl) city).setProduction(ARCHER);
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    // Treasury is increased by 6 each round. Archer costs 10
    assertThat(city.getTreasury(), is(2));
  }

  @Test
  void treasuryIsReducedBySettlerCostAfterSettlerProduction() {
    City city = game.getCityAt(new Position(1, 1));
    ((CityImpl) city).setProduction(SETTLER);
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    // Treasury is increased by 6 each round. Settler costs 30
    assertThat(city.getTreasury(), is(0));
  }


  // Producing a unit in a city that already has a unit stationed in it will place the new unit above the city,
  // placing more moving clockwise around the city
  @Test
  void producingAUnitInACityWithUnitAlreadyInCityPlacesUnitsAroundCity() {
    // check all positions clockwise around the city
    ProduceNewUnitAndCheckPosition(1, 1, notNullValue());
    ProduceNewUnitAndCheckPosition(0, 1, notNullValue());
    ProduceNewUnitAndCheckPosition(0, 2, notNullValue());
    ProduceNewUnitAndCheckPosition(1, 2, notNullValue());
    ProduceNewUnitAndCheckPosition(2, 2, nullValue());
    ProduceNewUnitAndCheckPosition(2, 1, notNullValue());
    ProduceNewUnitAndCheckPosition(2, 0, notNullValue());
    ProduceNewUnitAndCheckPosition(1, 0, nullValue());
    ProduceNewUnitAndCheckPosition(0, 0, notNullValue());
  }

  private void ProduceNewUnitAndCheckPosition(int i, int i2, Matcher<Object> test) {
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    skipOtherPlayersTurn();
    assertThat(game.getUnitAt(new Position(i, i2)), is(test));
  }






  /*
  import static org.junit.jupiter.api.Assertions.assertThrows;

  @Test
  public void testForException() throws NullPointerException {
     NullPointerException e = assertThrows(NullPointerException.class, () -> myMethod());
     assertThat(e.getMessage(), containsString("Illegal value: value causing error"));
  }
  */

  private void skipOtherPlayersTurn() {
    game.endOfTurn();
    game.endOfTurn();
  }

}