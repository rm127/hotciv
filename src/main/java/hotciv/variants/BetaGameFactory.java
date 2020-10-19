package hotciv.variants;

import hotciv.common.*;
import hotciv.framework.Game;

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

    public WorldLayoutStrategy createWorldLayoutStrategy(Game game) {
        return new AlphaWorldLayoutStrategy(game);
    }

    public BattleStrategy createBattleStrategy() {
        return new AttackerWinsBattleStrategy();
    }

    public UnitStatStrategy createUnitStatStrategy() {
        return new AlphaUnitStatStrategy();
    }

    public TileValidatorStrategy createTileValidatorStrategy() {
        return new AlphaTileValidator();
    }
}
