package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.variants.*;
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
        game = new GameImpl(new LinearGameAgingStrategy(), new AlwaysRedWinStrategy(), new GammaUnitActionStrategy());
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
        game.performUnitActionAt(position);
        assertThat(game.getCityAt(position), is(notNullValue()));
    }
}

/*
Test-list
- Archer has defense of 3
- Settler has defense of 3
- Settlers can found a city when performing action.
 */