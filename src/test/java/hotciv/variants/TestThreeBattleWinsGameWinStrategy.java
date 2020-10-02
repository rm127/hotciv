package hotciv.variants;

import hotciv.common.GameWinStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.jupiter.api.*;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestThreeBattleWinsGameWinStrategy {
    GameWinStrategy gws;
    HashMap<Position, City> cityMap;
    HashMap<Player, Integer> playerBattleStats;

    @BeforeEach
    public void setUp() {
        gws = new ThreeBattleWinsGameWinStrategy();
        cityMap = new HashMap<>();
        playerBattleStats = new HashMap<>();
        playerBattleStats.put(Player.RED, 0);
        playerBattleStats.put(Player.BLUE, 0);
    }

    // Player red wins after winning 3 battles
    @Test
    void playerRedShouldWinAfterWinning3Battles() {
        playerBattleStats.put(Player.RED, 3);
        assertThat(gws.getWinner(-4000, cityMap, playerBattleStats), is(Player.RED));
    }

    // No winner when no player have 3 battle wins
    @Test
    void noWinnerWhenNoOneHas3Wins() {
        assertThat(gws.getWinner(-4000, cityMap, playerBattleStats), is(nullValue()));
    }
}

/*
Test-list
- When a player reaches 3 battle wins said player should win
- When no players have reached 3 battle wins there shall be no winner
 */