package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;

/**
 * The strategy for deciding unit actions.
 * Pattern used: Strategy.
 */
public interface UnitActionStrategy {
    /**
     * Performs the action of the passed unit.
     * @param position the position of the unit to perform action.
     * @param game the game.
     */
    void performAction(Position position, Game game);
}
