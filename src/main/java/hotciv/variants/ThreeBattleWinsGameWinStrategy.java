package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.common.GameWinStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.HashMap;
import java.util.Map;

public class ThreeBattleWinsGameWinStrategy implements GameWinStrategy {
    public Player getWinner(GameImpl game) {
        for (Map.Entry<Player, Integer> entry : game.getPlayerBattleStats().entrySet()) {
            if (entry.getValue() >= 3) {
                return entry.getKey();
            }
        }
        return null;
    }
}
