package hotciv.variants;

import hotciv.common.GameWinStrategy;
import hotciv.framework.Player;

public class AlwaysRedWinStrategy implements GameWinStrategy {
    public Player getWinner(int gameAge) {
        if (gameAge >= -3000) {
            return Player.RED;
        }
        return null;
    }
}
