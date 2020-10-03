package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.UnitImpl;
import hotciv.common.Utilities;
import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy {
    public HashMap<Position, City> getCityMap() {
        final HashMap<Position, City> cityMap = new HashMap<>();

        cityMap.put(new Position(4,5), new CityImpl(Player.BLUE));
        cityMap.put(new Position(8,12), new CityImpl(Player.RED));

        return cityMap;
    }

    public HashMap<Position, Unit> getUnitMap() {
        final HashMap<Position, Unit> unitMap = new HashMap<>();

        unitMap.put(new Position(3,8), new UnitImpl(Player.RED, ARCHER));
        unitMap.put(new Position(4,4), new UnitImpl(Player.BLUE, LEGION));

        return unitMap;
    }

    public HashMap<Position, Tile> getTileMap() {
        String[] map = new String[] {
            "...ooMooooo.....",
            "..ohhoooofffoo..",
            ".oooooMooo...oo.",
            ".ooMMMoooo..oooo",
            "...ofooohhoooo..",
            ".ofoofooooohhoo.",
            "...ooo..........",
            ".ooooo.ooohooM..",
            ".ooooo.oohooof..",
            "offfoooo.offoooo",
            "oooooooo...ooooo",
            ".ooMMMoooo......",
            "..ooooooffoooo..",
            "....ooooooooo...",
            "..ooohhoo.......",
            ".....ooooooooo..",
        };
        return Utilities.convertStringsToMap(map);
    }
}
