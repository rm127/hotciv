package hotciv.variants;

import hotciv.standard.GameAgingStrategy;

public class LinearGameAgingStrategy implements GameAgingStrategy {
    public int calculateAgeJump() {
        return 100;
    }
}
