package hotciv.variants;

import hotciv.common.GameWinStrategy;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.HashMap;

public class AlwaysRedWinStrategy implements GameWinStrategy {
    public Player getWinner(int gameAge, HashMap<Position, City> cityMap) {
        if (gameAge >= -3000) {
            return Player.RED;
        }
        return null;
    }
}
