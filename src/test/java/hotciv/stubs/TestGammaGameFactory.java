package hotciv.stubs;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.Game;
import hotciv.variants.GammaGameFactory;

public class TestGammaGameFactory extends GammaGameFactory {
    public WorldLayoutStrategy createWorldLayoutStrategy(Game game) {
        return new TestGammaCivAlphaWorldLayoutStrategy(game);
    }
}
