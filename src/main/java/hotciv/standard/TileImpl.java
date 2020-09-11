package hotciv.standard;

import hotciv.framework.Tile;

import static hotciv.framework.GameConstants.MOUNTAINS;

public class TileImpl implements Tile {
    public String getTypeString() {
        return MOUNTAINS;
    }
}
