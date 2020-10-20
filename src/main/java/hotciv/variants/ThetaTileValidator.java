package hotciv.variants;

import hotciv.common.TileValidatorStrategy;

import static hotciv.framework.GameConstants.MOUNTAINS;
import static hotciv.framework.GameConstants.OCEANS;

public class ThetaTileValidator implements TileValidatorStrategy {
    private static final String CARAVAN = "caravan";
    private static final String DESERT = "desert";

    public boolean canMoveHere(String tileType, String unitType) {
        if (tileType.equals(DESERT) && !unitType.equals(CARAVAN)) {
            return false;
        }
        return !(tileType.equals(OCEANS) || tileType.equals(MOUNTAINS));
    }
}
