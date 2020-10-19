package hotciv.common;

/**
 * The strategy for supplying units with stats.
 * Pattern used: Strategy.
 */
public interface UnitStatStrategy {
    /**
     * returns the defense strength for the given unit type.
     * @param unitType The unit type of the unit.
     * @return The defense strength for the unit.
     */
    int getDefenseStrength(String unitType);

    /**
     * returns the attack strength for the given unit type.
     * @param unitType The unit type of the unit.
     * @return The attack strength for the unit.
     */
    int getAttackingStrength(String unitType);

    /**
     * returns the move count for the given unit type.
     * @param unitType The unit type of the unit.
     * @return The move count for the unit.
     */
    int getMoveCount(String unitType);
}
