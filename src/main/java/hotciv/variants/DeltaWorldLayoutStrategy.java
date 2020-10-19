package hotciv.variants;

import hotciv.common.*;
import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy {
    private final GameImpl game;

    public DeltaWorldLayoutStrategy(Game game) {
        this.game = (GameImpl) game;
    }

    public void createCities() {
        game.addCityAt(new Position(4,5), new CityImpl(Player.BLUE));
        game.addCityAt(new Position(8,12), new CityImpl(Player.RED));
    }

    public void createUnits() {
        game.addUnitAt(new Position(3,8), Player.RED, ARCHER);
        game.addUnitAt(new Position(4,4), Player.BLUE, LEGION);
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
