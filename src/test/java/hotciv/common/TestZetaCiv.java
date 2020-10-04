package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.UnitTestWorldLayoutStrategy;
import hotciv.variants.*;

import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestZetaCiv {
    private Game game;

    /**
     * Fixture for ZetaCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ZetaGameFactory());
    }

    // Before round 21 the winner is the player who takes over all cities
    @Test
    void beforeRound21TheWinnerIsWhoTakesOverAllCities() {
        game.moveUnit(new Position(4, 0), new Position(4,1));
        assertThat(game.getWinner(), is(Player.RED));
    }

    // After round 20 the winner is the player to first win 3 battles
    @Test
    void afterRound20TheWinnerIsWhoWins3Battles() {
        skipToRound21();
        game.moveUnit(new Position(1,1), new Position(2,1));
        game.moveUnit(new Position(1,5), new Position(1,6));
        game.moveUnit(new Position(1,8), new Position(1,9));
        assertThat(game.getWinner(), is(Player.RED));
    }

    // Winning 3 battles before round 21 doesn't result in a win
    @Test
    void winning3BattlesBeforeRound21YieldsNoWinner() {
        game.moveUnit(new Position(1,1), new Position(2,1));
        game.moveUnit(new Position(1,5), new Position(1,6));
        game.moveUnit(new Position(1,8), new Position(1,9));
        assertThat(game.getWinner(), is(nullValue()));
    }

    // Taking over all cities after round 20 doesn't result in a win
    @Test
    void takingOverAllCitiesAfterRound20YieldsNoWinner() {
        skipToRound21();
        game.moveUnit(new Position(4, 0), new Position(4,1));
        assertThat(game.getWinner(), is(nullValue()));
    }

    private void skipToRound21() {
        // skip to round 21
        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
    }
}


/*
Test-list
- Before and during round 20 the winner is the player who takes over all cities
- After round 20 the winner is the player to first win 3 battles
- Winning 3 battles before round 21 doesn't result in a win
- Taking over all cities after round 20 doesn't result in a win
 */