package hotciv.variants;

import hotciv.common.BattleStrategy;
import hotciv.common.GameImpl;
import hotciv.common.ModifierStrategy;
import hotciv.framework.Position;

public class AlgorithmBattleStrategy implements BattleStrategy {
    private final ModifierStrategy modifierStrategy;

    public AlgorithmBattleStrategy(ModifierStrategy modifierStrategy) {
        this.modifierStrategy = modifierStrategy;
    }

    public boolean executeBattle(GameImpl game, Position attackerPos, Position defenderPos) {
        return game.calculateAttackingStrength(attackerPos) * modifierStrategy.getModifier() > game.calculateDefensiveStrength(defenderPos) * modifierStrategy.getModifier();
    }
}
