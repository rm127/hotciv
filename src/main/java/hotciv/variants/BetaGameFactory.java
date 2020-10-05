package hotciv.variants;

import hotciv.common.*;

public class BetaGameFactory implements GameFactory {
    public GameAgingStrategy createGameAgingStrategy() {
        return new ProgressiveGameAgingStrategy();
    }

    public GameWinStrategy createGameWinStrategy() {
        return new CityDominationGameWinStrategy();
    }

    public UnitActionStrategy createUnitActionStrategy() {
        return new DoNothingUnitActionStrategy();
    }

    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

    public BattleStrategy createBattleStrategy() {
        return new AttackerWinsBattleStrategy();
    }
}
