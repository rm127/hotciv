package hotciv.common;

import hotciv.framework.Tile;

public class TileImpl implements Tile {
    private final String type;

    public TileImpl(String type) {
        this.type = type;
    }

    public String getTypeString() {
        return type;
    }
}
