package hotciv.stubs;

import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.ZetaGameFactory;

public class TestZetaGameFactory extends ZetaGameFactory {
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new UnitTestWorldLayoutStrategy();
    }
}
