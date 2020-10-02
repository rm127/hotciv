package hotciv.variants;

import hotciv.common.AttackStrategy;
import hotciv.common.GameImpl;
import hotciv.framework.Position;

public class AttackerWinsAttackStrategy implements AttackStrategy {
    public void computeWinner(GameImpl game, Position attackerPos, Position defenderPos) {
        game.removeUnitAt(defenderPos);
        game.moveUnit(attackerPos, defenderPos);
    }
}
