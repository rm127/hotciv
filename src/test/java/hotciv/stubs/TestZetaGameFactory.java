package hotciv.stubs;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.Game;
import hotciv.variants.ZetaGameFactory;

public class TestZetaGameFactory extends ZetaGameFactory {
    public WorldLayoutStrategy createWorldLayoutStrategy(Game game) {
        return new UnitTestWorldLayoutStrategy(game);
    }
}
