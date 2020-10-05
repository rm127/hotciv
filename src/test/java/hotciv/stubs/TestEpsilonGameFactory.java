package hotciv.stubs;

import hotciv.common.BattleStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.AlgorithmBattleStrategy;
import hotciv.variants.EpsilonGameFactory;

public class TestEpsilonGameFactory extends EpsilonGameFactory {
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new UnitTestWorldLayoutStrategy();
    }

    public BattleStrategy createBattleStrategy() {
        return new AlgorithmBattleStrategy(new fixedModifierStrategy());
    }
}
