package hotciv.variants;

import hotciv.common.*;
import hotciv.framework.Game;

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

    public WorldLayoutStrategy createWorldLayoutStrategy(Game game) {
        return new AlphaWorldLayoutStrategy(game);
    }

    public BattleStrategy createBattleStrategy() {
        return new AlgorithmBattleStrategy(new randomModifierStrategy());
    }

    public UnitStatStrategy createUnitStatStrategy() {
        return new AlphaUnitStatStrategy();
    }
}
