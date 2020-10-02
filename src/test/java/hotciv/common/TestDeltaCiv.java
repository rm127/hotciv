package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.GameConstants.OCEANS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class TestDeltaCiv {
    private Game game;

    /**
     * Fixture for BetaCiv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new LinearGameAgingStrategy(), new AlwaysRedWinStrategy(), new DoNothingUnitActionStrategy(), new DeltaWorldLayoutStrategy(), new AttackerWinsBattleStrategy());
    }

    // Mountains at tile (3,5)
    @Test
    void mountainsAtTile3_5() {
        assertThat(game.getTileAt(new Position(3,5)).getTypeString(), is(MOUNTAINS));
    }

    // Ocean at tile (13,2)
    @Test
    void oceanAtTile13_2() {
        assertThat(game.getTileAt(new Position(13,2)).getTypeString(), is(OCEANS));
    }

    // Red Archer at (3,8)
    @Test
    void redArcherAt3_8() {
        Unit unit = game.getUnitAt(new Position(3, 8));
        assertThat(unit.getTypeString(), is(ARCHER));
        assertThat(unit.getOwner(), is(Player.RED));
    }

    // Blue city at (4,5)
    @Test
    void blueCityAt4_5() {
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }
}