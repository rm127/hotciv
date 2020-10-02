package hotciv.variants;

import hotciv.common.AttackStrategy;
import hotciv.common.GameImpl;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class AttackerWinsAttackStrategy implements AttackStrategy {
    public void computeWinner(Game game, Position attacker, Position defender) {
        ((GameImpl) game).removeUnitAt(defender);
        game.moveUnit(attacker, defender);
    }
}
