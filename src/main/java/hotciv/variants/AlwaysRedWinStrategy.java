package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.common.GameWinStrategy;
import hotciv.framework.Player;

public class AlwaysRedWinStrategy implements GameWinStrategy {
    public Player getWinner(GameImpl game) {
        if (game.getAge() >= -3000) {
            return Player.RED;
        }
        return null;
    }
}
