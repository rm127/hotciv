package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.AlphaWorldLayoutStrategy;
import hotciv.variants.CityDominationGameWinStrategy;
import hotciv.variants.DoNothingUnitActionStrategy;
import hotciv.variants.ProgressiveGameAgingStrategy;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class TestBetaCiv {
    private Game game;

    /**
     * Fixture for BetaCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ProgressiveGameAgingStrategy(), new CityDominationGameWinStrategy(), new DoNothingUnitActionStrategy(), new AlphaWorldLayoutStrategy());
    }

    // No winner when cities have different owners.
    @Test
    void noWinnerWhenCitiesHaveDifferentOwners() {
        // a new game has cities owned by different players
        assertThat(game.getWinner(), is(nullValue()));
    }

    // Taking over all cities results in winning the game.
    @Test
    void takingOverAllCitiesResultsInAWin() {
        Position cityPosition = new Position(4,1);
        // move unit since we can only move one tile a time
        game.moveUnit(new Position(2, 0), new Position(3,0));
        skipOtherPlayersTurn();

        game.moveUnit(new Position(3, 0), cityPosition);
        assertThat(game.getWinner(), is(Player.RED));
    }

    // Time progresses as it should
    // TODO: Maybe rewrite to make more sense? It was the best way of writing it I could think of
    @Test
    void timeProgressesAsItShould() {
        int previousGameAge;

        // increase the gameAge until year 2200 (game is presumed ended)
        while (game.getAge() < 2200) {
            previousGameAge = game.getAge();
            skipOtherPlayersTurn();

            // Between 4000BC and 100BC 100 years pass per round.
            if (previousGameAge < -100) {
                assertThat(game.getAge(), is(previousGameAge + 100));

            //Around birth of Christ the sequence is -100, -1, +1, +50.
            } else if (previousGameAge == -100) {
                assertThat(game.getAge(), is(previousGameAge + 99));
            } else if (previousGameAge == -1) {
                assertThat(game.getAge(), is(previousGameAge + 2));
            } else if (previousGameAge == 1) {
                assertThat(game.getAge(), is(previousGameAge + 49));

            // Between 50AD and 1750 50 years pass per round.
            } else if (previousGameAge > 50 && previousGameAge < 1750) {
                assertThat(game.getAge(), is(previousGameAge + 50));

            // Between 1750 and 1900 25 years pass per round.
            } else if (previousGameAge > 1750 && previousGameAge < 1900) {
                assertThat(game.getAge(), is(previousGameAge + 25));

            // Between 1900 and 1970 5 years per round.
            } else if (previousGameAge > 1900 && previousGameAge < 1970) {
                assertThat(game.getAge(), is(previousGameAge + 5));

            // After 1970 1 year per round.
            } else if (previousGameAge > 1970) {
                assertThat(game.getAge(), is(previousGameAge + 1));
            }
        }
    }

    private void skipOtherPlayersTurn() {
        game.endOfTurn();
        game.endOfTurn();
    }
}

/*
Test-list
    - No winner when cities have different owners.
    - Taking over all cities results in winning the game.
    - Between 4000BC and 100BC 100 years pass per round.
    - Around birth of Christ the sequence is -100, -1, +1, +50.
    - Between 50AD and 1750 50 years pass per round.
    - Between 1750 and 1900 25 years pass per round.
    - Between 1900 and 1970 5 years per round.
    - After 1970 1 year per round.
 */