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
        game = new GameImpl(new LinearGameAgingStrategy(), new AlwaysRedWinStrategy(), new DoNothingUnitActionStrategy());
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
}

/*
Test-list
- Archer has defense of 3
- Settler has defense of 3
 */