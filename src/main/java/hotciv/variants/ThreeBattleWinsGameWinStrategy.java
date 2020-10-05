package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.common.GameWinStrategy;
import hotciv.framework.Player;

import java.util.HashMap;
import java.util.Map;

public class ThreeBattleWinsGameWinStrategy implements GameWinStrategy {
    private final HashMap<Player, Integer> playerBattleStats;

    public ThreeBattleWinsGameWinStrategy() {
        playerBattleStats = new HashMap<>();
        playerBattleStats.put(Player.RED, 0);
        playerBattleStats.put(Player.BLUE, 0);
    }

    public Player getWinner(GameImpl game) {
        for (Map.Entry<Player, Integer> entry : playerBattleStats.entrySet()) {
            if (entry.getValue() >= 3) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void incrementBattleWon(Player p) {
        playerBattleStats.put(p, playerBattleStats.get(p)+1);
    }

    public void incrementRoundNumber() {
    }
}
