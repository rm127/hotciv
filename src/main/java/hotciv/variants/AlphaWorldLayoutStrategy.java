package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.TileImpl;
import hotciv.common.UnitImpl;
import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.GameConstants.HILLS;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
    public HashMap<Position, City> getCityMap() {
        final HashMap<Position, City> cityMap = new HashMap<>();

        cityMap.put(new Position(1,1), new CityImpl(Player.RED));
        cityMap.put(new Position(4,1), new CityImpl(Player.BLUE));

        return cityMap;
    }

    public HashMap<Position, Unit> getUnitMap() {
        final HashMap<Position, Unit> unitMap = new HashMap<>();

        unitMap.put(new Position(2,0), new UnitImpl(Player.RED, ARCHER));
        unitMap.put(new Position(3,2), new UnitImpl(Player.BLUE, ARCHER));
        unitMap.put(new Position(4,3), new UnitImpl(Player.RED, SETTLER));

        return unitMap;
    }

    public HashMap<Position, Tile> getTileMap() {
        final HashMap<Position, Tile> tileMap = new HashMap<>();

        // make all tiles PLAINS
        for (int r = 0; r < 16; r++) {
            for (int c = 0; c < 16; c++) {
                tileMap.put(new Position(r,c), new TileImpl(PLAINS));
            }
        }

        // override the tiles that are not PLAINS
        tileMap.put(new Position(2,2), new TileImpl(MOUNTAINS));
        tileMap.put(new Position(1,0), new TileImpl(OCEANS));
        tileMap.put(new Position(0,1), new TileImpl(HILLS));

        return tileMap;
    }
}
