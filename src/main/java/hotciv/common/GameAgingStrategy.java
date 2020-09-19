package hotciv.common;

/**
 * The strategy for calculating game age.
 * Pattern used: Strategy.
 */
public interface GameAgingStrategy {
    /**
     * Return the amount of time to increase the game age with.
     * @return years to increase this time.
     * @param currentAge the current age of the game.
     */
    int calculateAgeIncrease(int currentAge);
}