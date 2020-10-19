package hotciv.variants;

import hotciv.common.*;
import hotciv.framework.Game;

public class GammaGameFactory implements GameFactory {
    public GameAgingStrategy createGameAgingStrategy() {
        return new LinearGameAgingStrategy();
    }

    public GameWinStrategy createGameWinStrategy() {
        return new AlwaysRedWinStrategy();
    }

    public UnitActionStrategy createUnitActionStrategy() {
        return new GammaUnitActionStrategy();
    }

    public WorldLayoutStrategy createWorldLayoutStrategy(Game game) {
        return new AlphaWorldLayoutStrategy(game);
    }

    public BattleStrategy createBattleStrategy() {
        return new AttackerWinsBattleStrategy();
    }

    public UnitStatStrategy createUnitStatStrategy() {
        return new AlphaUnitStatStrategy();
    }
}
