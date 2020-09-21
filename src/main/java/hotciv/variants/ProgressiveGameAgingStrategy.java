package hotciv.variants;

import hotciv.common.GameAgingStrategy;

/**
 * A progressive game aging strategy, based on the current age.
 */
public class ProgressiveGameAgingStrategy implements GameAgingStrategy {
    public int calculateAgeIncrease(int currentAge) {
        // Between 4000BC and 100BC
        if (currentAge < -100) {
            return 100;
            // Around birth of christ
        } else if (currentAge == -100) {
            return 99;
        } else if (currentAge == -1) {
            return 2;
        } else if (currentAge == 1) {
            return 49;
            // Between 50AD and 1750AD
        } else if (currentAge >= 50 && currentAge < 1750) {
            return 50;
        } else if (currentAge >= 1750 && currentAge < 1900){
            return 25;
        } else if (currentAge >= 1900 && currentAge < 1970) {
            return 5;
        }
        return 1;
    }
}
