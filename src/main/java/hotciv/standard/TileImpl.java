package hotciv.standard;

import hotciv.framework.Tile;

import static hotciv.framework.GameConstants.MOUNTAINS;

public class TileImpl implements Tile {
    private final String type;

    public TileImpl(String type) {
        this.type = type;
    }

    public String getTypeString() {
        return type;
    }
}
