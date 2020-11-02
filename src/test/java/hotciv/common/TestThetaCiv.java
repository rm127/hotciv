package hotciv.common;

import hotciv.framework.*;
import hotciv.variants.ThetaGameFactory;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.ARCHER;
import static hotciv.framework.GameConstants.OCEANS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class TestThetaCiv {
    private Game game;

    private static final String CARAVAN = "caravan";
    private static final String DESERT = "desert";

    /**
     * Fixture for BetaCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ThetaGameFactory());
    }

    // Caravan has attack strength of 1
    @Test
    void caravanShouldHaveAttackStrengthOf1() {
        assertThat(game.getUnitAt(new Position(9,6)).getAttackingStrength(), is(1));
    }

    // Caravan has defense strength of 4
    @Test
    void caravanShouldHaveDefenseStrengthOf4() {
        assertThat(game.getUnitAt(new Position(9,6)).getDefensiveStrength(), is(4));
    }

    // Caravan can move 2 tiles per round
    @Test
    void caravanCanMove2Tiles() {
        assertThat(game.getUnitAt(new Position(9,6)).getMoveCount(), is(2));
    }

    // Caravan unit costs 30
    @Test
    void caravanCosts30Production() {
        CityImpl city = (CityImpl) game.getCityAt(new Position(8, 12));
        city.setProduction(CARAVAN);
        assertThat(city.getProductionPrice(), is(30));
    }

    // Caravan can move into desert tiles
    @Test
    void caravanCanMoveOntoDesertTiles() {
        game.endOfTurn();
        boolean move = game.moveUnit(new Position(9,6), new Position(10, 6));
        assertThat(move, is(true));
    }

    // ONLY the caravan can move into desert tiles
    @Test
    void shouldNotBeAbleToMoveSettlerOntoDesertTile() {
        boolean move = game.moveUnit(new Position(5,5), new Position(6, 5));
        assertThat(move, is(false));
    }

    // When a caravan performs it's action in a city, the population increases by 2 and the unit gets removed.
    @Test
    void shouldIncreaseCityPopulationBy2IfPerformingActionInCityAndRemoveItself() {
        Position pos = new Position(8, 12);
        City city = game.getCityAt(pos);

        // produce a caravan in a city to avoid moving it across the map
        ((CityImpl) city).setProduction(CARAVAN);
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();

        int previousPopulation = city.getSize();
        game.performUnitActionAt(pos);
        int currentPopulation = city.getSize();

        assertThat(currentPopulation, is(previousPopulation+2));
        assertThat(game.getUnitAt(pos), is(nullValue()));
    }

    // A caravan doesn't do anything outside of a city
    @Test
    void shouldDoNothingWhenNotStandingInACity() {
        // change to blue
        game.endOfTurn();

        Position pos = new Position(9, 6);
        game.performUnitActionAt(pos);
        // unit still exists after "performing" action, hence not performed it's action
        assertThat(game.getUnitAt(pos), is(notNullValue()));
    }

    // A caravan can move two tiles each round
    @Test
    void shouldBeAbleToMoveTwoTiles() {
        Position oldPosition = new Position(9,6);
        Position newPosition = new Position(11,6);
        // change to blue player
        game.endOfTurn();

        Unit unit = game.getUnitAt(oldPosition);
        game.moveUnit(oldPosition, new Position(10, 6));
        game.moveUnit(new Position(10, 6), newPosition);
        assertThat(game.getUnitAt(newPosition), is(unit));
    }

    // A caravan can be produced
    @Test
    void shouldProduceACaravan() {
        Position position = new Position(8,12);
        ((CityImpl) game.getCityAt(position)).setProduction(CARAVAN);
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();
        skipOtherPlayersTurn();
        assertThat(game.getUnitAt(position).getTypeString(), is(CARAVAN));
    }

    // Blue city at 4, 5
    @Test
    void shouldHaveBlueCityAtPosition() {
        assertThat(game.getCityAt(new Position(4, 5)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(4, 5)).getOwner(), is(Player.BLUE));
    }

    // Red city at 8, 12
    @Test
    void shouldHaveRedCityAtPosition() {
        assertThat(game.getCityAt(new Position(8, 12)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(8, 12)).getOwner(), is(Player.RED));
    }

    // Blue caravan at 9, 6
    @Test
    void shouldBeBlueCaravanAtPosition() {
        Unit unit = game.getUnitAt(new Position(9, 6));
        assertThat(unit, is(notNullValue()));
        assertThat(unit.getOwner(), is(Player.BLUE));
        assertThat(unit.getTypeString(), is(CARAVAN));
    }

    // Red archer at 3, 8
    @Test
    void shouldBeRedArcherAtPosition() {
        Unit unit = game.getUnitAt(new Position(3, 8));
        assertThat(unit, is(notNullValue()));
        assertThat(unit.getOwner(), is(Player.RED));
        assertThat(unit.getTypeString(), is(ARCHER));
    }

    // Desert at 6, 4
    @Test
    void shouldBeDesert() {
        assertThat(game.getTileAt(new Position(6, 4)).getTypeString(), is(DESERT));
    }

    // Desert at 10, 3
    @Test
    void shouldAlsoBeDesert() {
        assertThat(game.getTileAt(new Position(10, 3)).getTypeString(), is(DESERT));
    }

    //Ocean at 14, 11
    @Test
    void shouldBeOcean() {
        assertThat(game.getTileAt(new Position(14, 11)).getTypeString(), is(OCEANS));
    }


    private void skipOtherPlayersTurn() {
        game.endOfTurn();
        game.endOfTurn();
    }
}

/*
Test-list
- Caravan has attack strength of 1
- Caravan has defense strength of 4
- Caravan can move 2 tiles per round
- Caravan unit costs 30
- Caravan can move onto desert tiles
- ONLY the caravan can move into desert tiles
- When a caravan performs it's action in a city, the population increases by 2
- A caravan doesn't do anything outside of a city owned by the same owner
- A caravan can move two tiles each round
- A caravan can be produced

- Blue city at 4, 5
- Red city at 8, 12
- Blue caravan at 9, 6
- Red archer at 3, 8
- Desert at 6, 4
- Desert at 10, 3
- Ocean at 14, 11
 */