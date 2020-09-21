package hotciv.variants;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.*;
import org.junit.jupiter.api.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeltaWorldLayoutStrategy {
    WorldLayoutStrategy wls;
    private HashMap<Position, City> cityMap;
    private HashMap<Position, Unit> unitMap;
    private HashMap<Position, Tile> tileMap;

    @BeforeEach
    public void setUp() {
        wls = new DeltaWorldLayoutStrategy();
        cityMap = wls.getCityMap();
        unitMap = wls.getUnitMap();
        tileMap = wls.getTileMap();
    }

    // Mountains at tile (3,4)
    @Test
    void mountainsAtTile3_4() {
        assertThat(tileMap.get(new Position(3,4)).getTypeString(), is(MOUNTAINS));
    }

    // Forest at tile (1,9)
    @Test
    void forestAtTile1_9() {
        assertThat(tileMap.get(new Position(1,9)).getTypeString(), is(FOREST));
    }

    // Ocean at tile (0,0) and (15,15)
    @Test
    void oceanAtCorners() {
        assertThat(tileMap.get(new Position(0,0)).getTypeString(), is(OCEANS));
        assertThat(tileMap.get(new Position(15,15)).getTypeString(), is(OCEANS));
    }

    // Hills at tile (14, 5)
    @Test
    void hillsAtTile14_5() {
        assertThat(tileMap.get(new Position(14,5)).getTypeString(), is(HILLS));
    }

    // Red Archer at (3,8)
    @Test
    void redArcherAt3_8() {
        Unit unit = unitMap.get(new Position(3,8));
        assertThat(unit.getTypeString(), is(ARCHER));
        assertThat(unit.getOwner(), is(Player.RED));
    }

    // Blue Legion at (4,4)
    @Test
    void blueLegionAt4_4() {
        Unit unit = unitMap.get(new Position(4,4));
        assertThat(unit.getTypeString(), is(LEGION));
        assertThat(unit.getOwner(), is(Player.BLUE));
    }

    // Red city at (8,12)
    @Test
    void redCityAt8_12() {
        assertThat(cityMap.get(new Position(8,12)).getOwner(), is(Player.RED));
    }

    // Blue city at (4,5)
    @Test
    void blueCityAt4_5() {
        assertThat(cityMap.get(new Position(4,5)).getOwner(), is(Player.BLUE));
    }
}

/*
Test-list
- Mountains at tile (3,4)
- Forest at tile (1,9)
- Ocean at tile (0,0)
- Ocean at tile (15,15)
- Hills at tile (14, 5)

- Red Archer at (3,8)
- Blue Legion at (4,4)

- Red city at (8,12)
- Blue city at (4,5)
 */