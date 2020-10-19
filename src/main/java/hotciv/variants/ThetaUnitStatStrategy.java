package hotciv.variants;

public class ThetaUnitStatStrategy extends AlphaUnitStatStrategy {
    private static final String CARAVAN = "caravan";

    public ThetaUnitStatStrategy() {
        super();

        // adding Caravan
        defenseStrengthMap.put(CARAVAN, 4);
        attackingStrengthMap.put(CARAVAN, 1);
        moveCountMap.put(CARAVAN, 2);
        costMap.put(CARAVAN, 30);
    }
}
