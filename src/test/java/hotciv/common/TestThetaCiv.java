package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.variants.ThetaGameFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class TestThetaCiv {
    private Game game;

    private static final String CARAVAN = "caravan";

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
}

/*
Test-list
- Caravan has attack strength of 1
- Caravan has defense strength of 4
- Caravan can move 2 tiles per round
- Caravan unit costs 30
- Caravan can move onto desert tiles
- ONLY the caravan can move into desert tiles
 */