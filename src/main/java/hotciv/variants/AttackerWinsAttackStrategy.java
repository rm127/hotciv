package hotciv.variants;

import hotciv.common.AttackStrategy;
import hotciv.common.GameImpl;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class AttackerWinsAttackStrategy implements AttackStrategy {
    public void computeWinner(Game game, Position attackerPos, Position defenderPos) {
        ((GameImpl) game).removeUnitAt(defenderPos);
        game.moveUnit(attackerPos, defenderPos);
    }
}
