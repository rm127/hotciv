package hotciv.variants;

import hotciv.common.UnitStatStrategy;

public class ThetaUnitStatStrategy implements UnitStatStrategy {
    private static final String CARAVAN = "caravan";
    private static UnitStatStrategy baseStrategy;

    public ThetaUnitStatStrategy() {
        baseStrategy = new AlphaUnitStatStrategy();
    }

    public int getDefenseStrength(String unitType) {
        if (unitType.equals(CARAVAN)) return 4;
        return baseStrategy.getDefenseStrength(unitType);
    }

    public int getAttackingStrength(String unitType) {
        if (unitType.equals(CARAVAN)) return 1;
        return baseStrategy.getAttackingStrength(unitType);
    }

    public int getMoveCount(String unitType) {
        if (unitType.equals(CARAVAN)) return 2;
        return baseStrategy.getMoveCount(unitType);
    }

    public int getCost(String unitType) {
        if (unitType.equals(CARAVAN)) return 30;
        return baseStrategy.getCost(unitType);
    }
}
