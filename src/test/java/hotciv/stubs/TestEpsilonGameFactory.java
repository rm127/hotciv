package hotciv.stubs;

import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.EpsilonGameFactory;

public class TestEpsilonGameFactory extends EpsilonGameFactory {
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new UnitTestWorldLayoutStrategy();
    }
}
