package hotciv.standard;

/**
 * The strategy for calculating game age.
 * Pattern used: Strategy.
 */
public interface GameAgingStrategy {
    /**
     * Return the amount of time to increase the game age with.
     * @return years to increase this time.
     */
    int calculateAgeJump();
}