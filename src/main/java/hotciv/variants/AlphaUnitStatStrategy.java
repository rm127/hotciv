package hotciv.variants;

import hotciv.common.UnitStatStrategy;

import static hotciv.framework.GameConstants.*;

public class AlphaUnitStatStrategy implements UnitStatStrategy {
    public int getDefenseStrength(String unitType) {
        switch (unitType) {
            case SETTLER:
            case ARCHER:
                return 3;
            case LEGION:
                return 2;
        }
        return 0;
    }

    public int getAttackingStrength(String unitType) {
        switch (unitType) {
            case ARCHER:
                return 2;
            case LEGION:
                return 4;
            case SETTLER:
                return 0;
        }
        return 0;
    }

    public int getMoveCount(String unitType) {
        return 1;
    }
}
