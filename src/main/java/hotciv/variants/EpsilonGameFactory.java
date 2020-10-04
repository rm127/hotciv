package hotciv.variants;

import hotciv.common.*;

public class EpsilonGameFactory implements GameFactory {
    public GameAgingStrategy createGameAgingStrategy() {
        return new LinearGameAgingStrategy();
    }

    public GameWinStrategy createGameWinStrategy() {
        return new ThreeBattleWinsGameWinStrategy();
    }

    public UnitActionStrategy createUnitActionStrategy() {
        return new DoNothingUnitActionStrategy();
    }

    // TODO: Are we allowed to use this TestWorldLayout for easier testing or do we have to use the AlphaWorldLayout?
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new UnitTestWorldLayoutStrategy();
    }

    public BattleStrategy createBattleStrategy() {
        return new AlgorithmBattleStrategy();
    }
}
