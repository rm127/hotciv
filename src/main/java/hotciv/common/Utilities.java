package hotciv.common;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;

import java.util.HashMap;

public class Utilities {
    public static HashMap<Position, Tile> convertStringsToMap(String[] strings) {
        final HashMap<Position, Tile> tileMap = new HashMap<>();

        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = strings[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                tileMap.put(new Position(r,c), new TileImpl(type));
            }
        }

        return tileMap;
    }
}
