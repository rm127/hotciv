package hotciv.stubs;

import hotciv.common.CityImpl;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.AlphaWorldLayoutStrategy;

import java.util.HashMap;

public class TestGammaCivAlphaWorldLayoutStrategy extends AlphaWorldLayoutStrategy {
    public HashMap<Position, City> getCityMap() {
        final HashMap<Position, City> cityMap = new HashMap<>();

        cityMap.put(new Position(1,1), new CityImpl(Player.RED));
        cityMap.put(new Position(4,1), new CityImpl(Player.BLUE));

        cityMap.put(new Position(15,15), new CityImpl(Player.RED));

        return cityMap;
    }
}
