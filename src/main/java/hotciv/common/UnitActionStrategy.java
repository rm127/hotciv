package hotciv.common;

import hotciv.framework.Unit;

/**
 * The strategy for deciding unit actions.
 * Pattern used: Strategy.
 */
public interface UnitActionStrategy {
    /**
     * Performs the action of the passed unit.
     * @param unit the unit which action to perform.
     */
    void performAction(Unit unit);
}
