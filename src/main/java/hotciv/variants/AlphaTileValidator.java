package hotciv.variants;

import hotciv.common.TileValidatorStrategy;

import static hotciv.framework.GameConstants.MOUNTAINS;
import static hotciv.framework.GameConstants.OCEANS;

public class AlphaTileValidator implements TileValidatorStrategy {
    public boolean canMoveHere(String tileType, String unitType) {
        return !(tileType.equals(OCEANS) || tileType.equals(MOUNTAINS));
    }
}
