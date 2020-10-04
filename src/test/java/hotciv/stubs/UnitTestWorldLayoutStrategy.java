package hotciv.stubs;

import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.common.Utilities;
import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class UnitTestWorldLayoutStrategy implements WorldLayoutStrategy {
    public HashMap<Position, City> getCityMap() {
        final HashMap<Position, City> cityMap = new HashMap<>();

        cityMap.put(new Position(1,1), new CityImpl(Player.RED));

        return cityMap;
    }

    public HashMap<Position, Unit> getUnitMap() {
        final HashMap<Position, Unit> unitMap = new HashMap<>();

        unitMap.put(new Position(1,1), new UnitImpl(Player.RED, LEGION));
        unitMap.put(new Position(0,1), new UnitImpl(Player.RED, ARCHER));
        unitMap.put(new Position(1,0), new UnitImpl(Player.RED, SETTLER));
        unitMap.put(new Position(2,1), new UnitImpl(Player.BLUE, ARCHER));

        unitMap.put(new Position(1,6), new UnitImpl(Player.BLUE, ARCHER));
        unitMap.put(new Position(1,5), new UnitImpl(Player.RED, LEGION));

        unitMap.put(new Position(1,9), new UnitImpl(Player.BLUE, SETTLER));
        unitMap.put(new Position(1,8), new UnitImpl(Player.RED, ARCHER));

        return unitMap;
    }

    public HashMap<Position, Tile> getTileMap() {
        String[] map = new String[] {
                "oooooooooooooooo",
                "oooooffooooooooo",
                "ohoooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
        };
        return Utilities.convertStringsToMap(map);
    }
}
