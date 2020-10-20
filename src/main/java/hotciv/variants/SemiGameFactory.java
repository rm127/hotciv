package hotciv.variants;

import hotciv.common.*;
import hotciv.framework.Game;

public class SemiGameFactory implements GameFactory {
    public GameAgingStrategy createGameAgingStrategy() {
        return new ProgressiveGameAgingStrategy();
    }

    public GameWinStrategy createGameWinStrategy() {
        return new ThreeBattleWinsGameWinStrategy();
    }

    public UnitActionStrategy createUnitActionStrategy() {
        return new GammaUnitActionStrategy();
    }

    public WorldLayoutStrategy createWorldLayoutStrategy(Game game) {
        return new DeltaWorldLayoutStrategy(game);
    }

    public BattleStrategy createBattleStrategy() {
        return new AlgorithmBattleStrategy(new randomModifierStrategy());
    }

    public UnitStatStrategy createUnitStatStrategy() {
        return new AlphaUnitStatStrategy();
    }

    public TileValidatorStrategy createTileValidatorStrategy() {
        return new AlphaTileValidator();
    }
}
