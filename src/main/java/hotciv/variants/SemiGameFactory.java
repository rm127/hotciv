package hotciv.variants;

import hotciv.common.*;

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

    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
    }

    public BattleStrategy createBattleStrategy() {
        return new AlgorithmBattleStrategy(new randomModifierStrategy());
    }
}
