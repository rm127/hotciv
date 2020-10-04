package hotciv.variants;

import hotciv.common.BattleStrategy;
import hotciv.common.GameImpl;
import hotciv.framework.Position;

public class AlgorithmBattleStrategy implements BattleStrategy {
    public boolean executeBattle(GameImpl game, Position attackerPos, Position defenderPos) {
        return game.calculateAttackingStrength(attackerPos) * getRandomNumber() > game.calculateDefensiveStrength(defenderPos) * getRandomNumber();
    }

    // TODO: Implement proper random algorithm
    // TODO: How to test this with proper algorithm?
    private int getRandomNumber() {
        return 1;
    }
}
