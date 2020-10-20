package hotciv.stubs;

import hotciv.common.BattleStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.Game;
import hotciv.variants.AlgorithmBattleStrategy;
import hotciv.variants.EpsilonGameFactory;

public class TestEpsilonGameFactory extends EpsilonGameFactory {
    public WorldLayoutStrategy createWorldLayoutStrategy(Game game) {
        return new UnitTestWorldLayoutStrategy(game);
    }

    public BattleStrategy createBattleStrategy() {
        return new AlgorithmBattleStrategy(new fixedModifierStrategy());
    }
}
