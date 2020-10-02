package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.GameWinStrategy;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.jupiter.api.*;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCityDominationGameWinStrategy {
    GameWinStrategy gws;
    HashMap<Position, City> cityMap;
    HashMap<Player, Integer> playerBattleStats;

    @BeforeEach
    public void setUp() {
        gws = new CityDominationGameWinStrategy();
        playerBattleStats = new HashMap<>();
        // create new cityMap
        cityMap = new HashMap<>();
    }

    // No winner when cities have different owners
    @Test
    void noWinnerWhenCitiesHaveDifferentOwners() {
        // add a Red city
        cityMap.put(new Position(0,0), new CityImpl(Player.RED));
        // add a Blue city
        cityMap.put(new Position(0,2), new CityImpl(Player.BLUE));
        assertThat(gws.getWinner(-4000, cityMap, playerBattleStats), is(nullValue()));
    }

    // When all cities are owned by the same player, that player has won. Test for player Red.
    @Test
    void playerRedWinsWhenOwningAllCitiesInTheWorld() {
        // create new cityMap
        HashMap<Position, City> cityMap = new HashMap<>();
        // add a Red city
        cityMap.put(new Position(0,0), new CityImpl(Player.RED));
        // add another Red city
        cityMap.put(new Position(0,2), new CityImpl(Player.RED));
        assertThat(gws.getWinner(-4000, cityMap, playerBattleStats), is(Player.RED));
    }

    // When all cities are owned by the same player, that player has won. Test for player Blue.
    @Test
    void playerBlueWinsWhenOwningAllCitiesInTheWorld() {
        // create new cityMap
        HashMap<Position, City> cityMap = new HashMap<>();
        // add a Red city
        cityMap.put(new Position(0,0), new CityImpl(Player.BLUE));
        // add another Red city
        cityMap.put(new Position(0,2), new CityImpl(Player.BLUE));
        assertThat(gws.getWinner(-4000, cityMap, playerBattleStats), is(Player.BLUE));
    }
}

/*
Test-list
- No winner when cities have different owners.
- When all cities are owned by the same player, that player has won.
 */