package hotciv.variants;

import hotciv.common.*;
import hotciv.framework.Game;

public class ZetaGameFactory implements GameFactory {
    public GameAgingStrategy createGameAgingStrategy() {
        return new LinearGameAgingStrategy();
    }

    public GameWinStrategy createGameWinStrategy() {
        return new AlternatingGameWinStrategy(new CityDominationGameWinStrategy(), new ThreeBattleWinsGameWinStrategy());
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
}
