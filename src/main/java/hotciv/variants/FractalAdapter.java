package hotciv.variants;

import hotciv.common.Utilities;
import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;

public class FractalAdapter implements WorldLayoutStrategy {
    private final ThirdPartyFractalGenerator generator;

    public FractalAdapter() {
        this.generator = new ThirdPartyFractalGenerator();
    }

    public void createCities() {

    }

    public void createUnits() {

    }

    public HashMap<Position, Tile> getTileMap() {
        String[] map = new String[16];
        for (int r = 0; r < 16; r++) {
            for (int c = 0; c < 16; c++) {
                map[r] += generator.getLandscapeAt(r, c);
            }
        }
        return Utilities.convertStringsToMap(map);
    }
}
