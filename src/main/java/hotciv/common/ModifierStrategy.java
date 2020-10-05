package hotciv.common;

/**
 * The strategy for finding modifier numbers.
 * Pattern used: Strategy.
 */
public interface ModifierStrategy {

    /**
     * Returns a modifier number.
     * @return the modifier.
     */
    int getModifier();
}
