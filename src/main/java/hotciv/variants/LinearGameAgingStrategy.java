package hotciv.variants;

import hotciv.common.GameAgingStrategy;

public class LinearGameAgingStrategy implements GameAgingStrategy {
    public int calculateAgeIncrease(int currentAge) {
        return 100;
    }
}
