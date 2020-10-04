package hotciv.common;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.stubs.UnitTestWorldLayoutStrategy;
import hotciv.framework.Game;
import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonCiv {
    private Game game;

    /**
     * Fixture for EpsilonCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new LinearGameAgingStrategy(), new ThreeBattleWinsGameWinStrategy(), new DoNothingUnitActionStrategy(), new UnitTestWorldLayoutStrategy(), new AlgorithmBattleStrategy());
    }

    // Archer has defense of 3 and attack of 2
    @Test
    void archerHasDefenseStrengthOf3andAttackStrengthOf2() {
        Unit archer = game.getUnitAt(new Position(0,1));
        assertThat(archer.getDefensiveStrength(), is(3));
        assertThat(archer.getAttackingStrength(), is(2));
    }

    // Legion has defense of 2 and attack of 4
    @Test
    void legionHasDefenseStrengthOf2andAttackStrengthOf4() {
        Unit legion = game.getUnitAt(new Position(1,1));
        assertThat(legion.getDefensiveStrength(), is(2));
        assertThat(legion.getAttackingStrength(), is(4));
    }

    // Settler has defense of 3 and attack of 0
    @Test
    void settlerHasDefenseStrengthOf3andAttackStrengthOf0() {
        Unit settler = game.getUnitAt(new Position(1,0));
        assertThat(settler.getDefensiveStrength(), is(3));
        assertThat(settler.getAttackingStrength(), is(0));
    }

    // When no players have reached 3 battle wins there shall be no winner
    @Test
    void noBattleWinsForEitherPlayerEqualsNoWinner() {
        assertThat(game.getWinner(), is(nullValue()));
    }

    // A Legion in a city with two adjacent units results in (4 + 1 + 1) * 3 = 18 attack strength
    @Test
    void legionInCityWithFriendsShouldHaveAttackingStrengthOf18() {
        int attackingStrength = ((GameImpl) game).calculateAttackingStrength(new Position(1,1));
        assertThat(attackingStrength, is(18));
    }

    // An Archer in a forest with no adjacent units results in 3 * 2 = 6 defense strength
    @Test
    void archerInForestShouldHaveDefensiveStrengthOf6() {
        int defensiveStrength = ((GameImpl) game).calculateDefensiveStrength(new Position(1,6));
        assertThat(defensiveStrength, is(6));
    }

    // An Archer on a hill with no adjacent units results in 2 * 2 = 4 attack strength
    @Test
    void archerOnHillShouldHaveAttackingStrengthOf4() {
        int attackingStrength = ((GameImpl) game).calculateAttackingStrength(new Position(2,1));
        assertThat(attackingStrength, is(4));
    }

    // A Legion with attack strength of 4 * 2 = 8 wins over Archer with 3 * 2 = 6 defense strength
    @Test
    void legionWithAttackingStrengthGreaterThanArcherWinsBattle() {
        Position attackerPosition = new Position(1,5);
        Position defenderPosition = new Position(1,6);
        boolean wonTheBattle = game.moveUnit(attackerPosition, defenderPosition);
        assertThat(wonTheBattle, is(true));
    }

    // Archer with 3 * 2 = 6 defense strength looses to A Legion with attack strength of 4 * 2 = 8
    @Test
    void archerWithAttackingStrengthLowerThanLegionLoosesBattle() {
        game.endOfTurn();
        Position attackerPosition = new Position(1,6);
        Position defenderPosition = new Position(1,5);
        boolean wonTheBattle = game.moveUnit(attackerPosition, defenderPosition);
        assertThat(wonTheBattle, is(false));
    }

    // When a player reaches 3 battle wins said player should win
    @Test
    void aPlayerWinsWhenHavingWon3Battles() {
        game.moveUnit(new Position(1,1), new Position(2,1));
        game.moveUnit(new Position(1,5), new Position(1,6));
        game.moveUnit(new Position(1,8), new Position(1,9));
        assertThat(game.getWinner(), is(Player.RED));
    }
}

/*
Test-list
- When a player reaches 3 battle wins said player should win

- When no players have reached 3 battle wins there shall be no winner
- A Legion in a city with two adjacent units results in (4 + 1 + 1) * 3 = 18 attack strength
- An Archer in a forest with no adjacent units results in 3 * 2 = 6 defense strength
- An Archer on a hill with no adjacent units results in 2 * 2 = 4 attack strength
- A Legion with attack strength of 4 * 2 = 8 wins over Archer with 3 * 2 = 6 defense strength
- Archer with 3 * 2 = 6 defense strength looses to A Legion with attack strength of 4 * 2 = 8

- Archer has defense of 3
- Archer has attack of 2
- Legion has defense of 2
- Legion has attack of 4
- Settler has defense of 3
- Settler has attack of 0
 */
