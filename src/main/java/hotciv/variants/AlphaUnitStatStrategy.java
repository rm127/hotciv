package hotciv.variants;

import hotciv.common.UnitStatStrategy;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class AlphaUnitStatStrategy implements UnitStatStrategy {
    final HashMap<String, Integer> defenseStrengthMap;
    final HashMap<String, Integer> attackingStrengthMap;
    final HashMap<String, Integer> moveCountMap;
    final HashMap<String, Integer> costMap;

    public AlphaUnitStatStrategy() {
        defenseStrengthMap = new HashMap<>();
        attackingStrengthMap = new HashMap<>();
        moveCountMap = new HashMap<>();
        costMap = new HashMap<>();

        // adding Archer
        defenseStrengthMap.put(ARCHER, 3);
        attackingStrengthMap.put(ARCHER, 2);
        moveCountMap.put(ARCHER, 1);
        costMap.put(ARCHER, 10);

        // adding Legion
        defenseStrengthMap.put(LEGION, 2);
        attackingStrengthMap.put(LEGION, 4);
        moveCountMap.put(LEGION, 1);
        costMap.put(LEGION, 15);

        // adding Settler
        defenseStrengthMap.put(SETTLER, 3);
        attackingStrengthMap.put(SETTLER, 0);
        moveCountMap.put(SETTLER, 1);
        costMap.put(SETTLER, 30);
    }


    public int getDefenseStrength(String unitType) {
        return defenseStrengthMap.get(unitType);
    }

    public int getAttackingStrength(String unitType) {
        return attackingStrengthMap.get(unitType);
    }

    public int getMoveCount(String unitType) {
        return moveCountMap.get(unitType);
    }

    public int getCost(String unitType) {
        return costMap.get(unitType);
    }
}
