package hotciv.variants;

import hotciv.common.*;
import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class ThetaWorldLayoutStrategy implements WorldLayoutStrategy {
    private final GameImpl game;
    private static final String CARAVAN = "caravan";

    public ThetaWorldLayoutStrategy(Game game) {
        this.game = (GameImpl) game;
    }

    public void createCities() {
        game.addCityAt(new Position(4,5), Player.BLUE);
        game.addCityAt(new Position(8,12), Player.RED);
    }

    public void createUnits() {
        game.addUnitAt(new Position(4,4), Player.BLUE, LEGION);
        game.addUnitAt(new Position(3,8), Player.RED, ARCHER);
        game.addUnitAt(new Position(5,5), Player.RED, SETTLER);
        game.addUnitAt(new Position(9,6), Player.BLUE, CARAVAN);
    }

    public HashMap<Position, Tile> getTileMap() {
        String[] map = new String[] {
                "...ooModdoo.....",
                "..ohhoooofffoo..",
                ".oooooMooo...oo.",
                ".ooMMMoooo..oooo",
                "...ofooohhoooo..",
                ".ofoofooooohhoo.",
                "...odd..........",
                ".ooodo.ooohooM..",
                ".ooooo.oohooof..",
                "offfoddo.offoooo",
                "oodddodo...ooooo",
                ".ooMMMdooo......",
                "..ooooooffoooo..",
                "....ooooooooo...",
                "..ooohhoo.......",
                ".....ooooooooo..",
        };
        return Utilities.convertStringsToMap(map);
    }
}