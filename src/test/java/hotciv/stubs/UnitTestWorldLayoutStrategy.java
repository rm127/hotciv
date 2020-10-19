package hotciv.stubs;

import hotciv.common.*;
import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class UnitTestWorldLayoutStrategy implements WorldLayoutStrategy {
    private final GameImpl game;

    public UnitTestWorldLayoutStrategy(Game game) {
        this.game = (GameImpl) game;
    }

    public void createCities() {
        game.addCityAt(new Position(1,1), Player.RED);
        game.addCityAt(new Position(4,1), Player.BLUE);
    }

    public void createUnits() {
        game.addUnitAt(new Position(1,1), Player.RED, LEGION);
        game.addUnitAt(new Position(0,1), Player.RED, ARCHER);
        game.addUnitAt(new Position(1,0), Player.RED, SETTLER);
        game.addUnitAt(new Position(2,1), Player.BLUE, ARCHER);
        game.addUnitAt(new Position(2,2), Player.RED, SETTLER);

        game.addUnitAt(new Position(1,6), Player.BLUE, ARCHER);
        game.addUnitAt(new Position(1,5), Player.RED, LEGION);

        game.addUnitAt(new Position(1,9), Player.BLUE, SETTLER);
        game.addUnitAt(new Position(1,8), Player.RED, ARCHER);

        game.addUnitAt(new Position(4,0), Player.RED, ARCHER);

        game.addUnitAt(new Position(8,8), Player.RED, LEGION);
        game.addUnitAt(new Position(8,9), Player.BLUE, SETTLER);
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
