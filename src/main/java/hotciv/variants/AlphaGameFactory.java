package hotciv.variants;

import hotciv.common.*;

public class AlphaGameFactory implements GameFactory {
    public GameAgingStrategy createGameAgingStrategy() {
        return new LinearGameAgingStrategy();
    }

    public GameWinStrategy createGameWinStrategy() {
        return new AlwaysRedWinStrategy();
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
