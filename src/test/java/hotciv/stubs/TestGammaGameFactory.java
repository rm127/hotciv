package hotciv.stubs;

import hotciv.common.WorldLayoutStrategy;
import hotciv.variants.GammaGameFactory;

public class TestGammaGameFactory extends GammaGameFactory {
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new TestGammaCivAlphaWorldLayoutStrategy();
    }
}
