package hotciv.variants;

import hotciv.common.BattleStrategy;
import hotciv.common.GameImpl;
import hotciv.framework.Position;

public class AttackerWinsBattleStrategy implements BattleStrategy {
    public void executeBattle(GameImpl game, Position attackerPos, Position defenderPos) {
        game.removeUnitAt(defenderPos);
        game.moveUnit(attackerPos, defenderPos);
    }
}
