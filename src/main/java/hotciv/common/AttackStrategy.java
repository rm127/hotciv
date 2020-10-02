package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Position;

/**
 * The strategy for deciding who wins a battle between two units.
 * Pattern used: Strategy.
 */
public interface AttackStrategy {
    /**
     * Computes the winner of a battle between two units.
     * @param game Game instance for handling move and deletion of units.
     * @param attackerPos The position of the attacking unit.
     * @param defenderPos The position of the defending unit.
     */
    void computeWinner(GameImpl game, Position attackerPos, Position defenderPos);
}
