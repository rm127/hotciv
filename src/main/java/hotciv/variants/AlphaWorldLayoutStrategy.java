package hotciv.variants;

import hotciv.common.*;
import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.GameConstants.HILLS;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
    private final GameImpl game;

    public AlphaWorldLayoutStrategy(Game game) {
        this.game = (GameImpl) game;
    }

    public void createCities() {
        game.addCityAt(new Position(1,1), new CityImpl(Player.RED));
        game.addCityAt(new Position(4,1), new CityImpl(Player.BLUE));
    }

    public void createUnits() {
        game.addUnitAt(new Position(2,0), Player.RED, ARCHER);
        game.addUnitAt(new Position(3,2), Player.BLUE, ARCHER);
        game.addUnitAt(new Position(4,3), Player.RED, SETTLER);
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
