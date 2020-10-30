package hotciv.stubs;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.Game;
import hotciv.variants.AlphaGameFactory;
import hotciv.variants.FractalAdapter;

public class TestFractalAdapterGameFactory extends AlphaGameFactory {
    public WorldLayoutStrategy createWorldLayoutStrategy(Game game) {
        return new FractalAdapter();
    }
}
