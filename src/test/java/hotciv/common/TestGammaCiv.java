package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.stubs.TestGammaGameFactory;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class TestGammaCiv {

    private Game game;

    /**
     * Fixture for BetaCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new TestGammaGameFactory());
    }

    // Archer has defense of 3
    @Test
    void archerHasDefenseOf3() {
        assertThat(game.getUnitAt(new Position(2, 0)).getDefensiveStrength(), is(3));
    }

    // Settler has defense of 3
    @Test
    void settlerHasDefenseOf3() {
        assertThat(game.getUnitAt(new Position(4, 3)).getDefensiveStrength(), is(3));
    }

    // Settler action founds a city
    @Test
    void SettlersActionFoundsCity() {
        // There is a settler at position 4,3
        Position position = new Position(4,3);
        // Perform action
        game.performUnitActionAt(position);
        assertThat(game.getCityAt(position), is(notNullValue()));
    }

    // Settler unit itself is removed after performing action
    @Test
    void SettlerIsRemovedAfterPerformingAction() {
        Position position = new Position(4, 3);
        game.performUnitActionAt(position);
        assertThat(game.getUnitAt(position), is(nullValue()));
    }

    // Settler's founded city is owned by player who owned the unit
    @Test
    void SettlersFoundedCityIsOwnedByTheOwnerOfTheSettler() {
        // The settler is owned by player red
        Position position = new Position(4,3);
        game.performUnitActionAt(position);
        assertThat(game.getCityAt(position).getOwner(), is(Player.RED));
    }

    // Only the owner of the unit can perform its unit action
    @Test
    void OnlyTheOwnerOfTheUnitCanPerformItsUnitAction() {
        // The settler is owned by player red
        Position position = new Position(4,3);
        game.endOfTurn();
        game.performUnitActionAt(position);
        assertThat(game.getCityAt(position), is(nullValue()));
    }

    // Archers' defense doubles when performing action
    @Test
    void ArchersDefenseDoublesWhenPerformingUnitAction() {
        // There is a red archer at position 2,0
        Position position = new Position(2, 0);
        Unit archer = game.getUnitAt(position);
        // Get the original/starting defense strength
        int originalDefenseStrength = archer.getDefensiveStrength();
        game.performUnitActionAt(position);
        assertThat(archer.getDefensiveStrength(), is(originalDefenseStrength * 2));
    }

    // Archers' cannot move while fortified (performing action)
    @Test
    void ArchersCannotMoveWhileFortified() {
        Position archerPosition = new Position(2, 0);
        game.performUnitActionAt(archerPosition);
        Position moveToPosition = new Position(3, 0);
        // Should not be able to move archer after performing its action
        assertThat(game.moveUnit(archerPosition, moveToPosition), is(false));
    }

    // If an archer fortifies while already fortified, it removes the fortification
    @Test
    void ArcherRemovesFortificationIfAlreadyFortifiedWhenPerformingAction() {
        Position position = new Position(2, 0);
        Unit archer = game.getUnitAt(position);
        // Perform action - fortify
        game.performUnitActionAt(position);
        // Skip other player
        game.endOfTurn();
        game.endOfTurn();
        // Perform action - remove fortification
        game.performUnitActionAt(position);
        assertThat(((UnitImpl) archer).isFortified(), is(false));
    }

    // Producing units in a city places them clockwise around the city. Shall not return positions outside the world.
    // Cannot be tested with only 2 fixed cities.
    @Test
    void shouldOnlyProduceUnitsAtLegalPositions() {
        // check all positions clockwise around the city
        ProduceNewUnitAndCheckPosition(15, 15, notNullValue());
        ProduceNewUnitAndCheckPosition(14, 15, notNullValue());
        ProduceNewUnitAndCheckPosition(14, 16, nullValue());
        ProduceNewUnitAndCheckPosition(15, 16, nullValue());
        ProduceNewUnitAndCheckPosition(16, 16, nullValue());
        ProduceNewUnitAndCheckPosition(16, 15, nullValue());
        ProduceNewUnitAndCheckPosition(16, 14, nullValue());
        ProduceNewUnitAndCheckPosition(15, 14, notNullValue());
        ProduceNewUnitAndCheckPosition(14, 14, notNullValue());
    }

    private void ProduceNewUnitAndCheckPosition(int r, int c, Matcher<Object> test) {
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();
        assertThat(game.getUnitAt(new Position(r, c)), is(test));
    }

    private void skipOtherPlayersTurn() {
        game.endOfTurn();
        game.endOfTurn();
    }
}



/*
Test-list
- Archer has defense of 3
- Settler has defense of 3
- Settlers can found a city when performing action.
- Only the owner of the unit can perform its unit action
- Archers' defense doubles when performing action
- Archers' cannot move while fortified (performing action)
- If an archer fortifies while already fortified, it removes the fortification
- Archers' defense doubles each round it is fortified
- Producing units in a city places them clockwise around the city. Shall not return positions outside the world. Cannot be tested with only 2 fixed cities.
 */