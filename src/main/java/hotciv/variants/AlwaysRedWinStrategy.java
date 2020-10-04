package hotciv.variants;

import hotciv.common.GameWinStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.HashMap;

public class AlwaysRedWinStrategy implements GameWinStrategy {
    public Player getWinner(int gameAge, HashMap<Position, City> cityMap, HashMap<Player, Integer> playerBattleStats, int currentRound) {
        if (gameAge >= -3000) {
            return Player.RED;
        }
        return null;
    }
}
