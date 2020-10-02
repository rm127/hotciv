package hotciv.common;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

    public static Iterator<Position> getAdjacentPositions(Position position) {
        ArrayList<Position> list = new ArrayList<>();

        // list of modifiers to current position
        int[] columns = new int[] {0, 0, 1, 1, 1, 0, -1, -1, -1};
        int[] rows    = new int[] {0, -1, -1, 0, 1, 1, 1, 0, -1};

        for (int i = 0; i < 9; i++) {
            Position newPosition = new Position(position.getRow() + rows[i], position.getColumn() + columns[i]);
            list.add(newPosition);
        }

        return list.iterator();
    }
}
