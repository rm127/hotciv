package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.common.GameWinStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.Map;

public class CityDominationGameWinStrategy implements GameWinStrategy {
    public Player getWinner(GameImpl game) {
        Player dominatingPlayer = null;

        // loop through cities in world
        for (Map.Entry<Position, City> entry : game.getCities().entrySet()) {
            City city = entry.getValue();

            // if first city, then assign owner to variable
            if (dominatingPlayer == null) {
                dominatingPlayer = city.getOwner();
                // if not first city, check if owner of this city is the same
                // as the previous. If not, return null.
            } else if (dominatingPlayer != city.getOwner()) {
                return null;
            }
        }
        // if all cities are the same, then the owner will be returned. Else null.
        return dominatingPlayer;
    }

    public void incrementBattleWon(Player p) {

    }

    public void incrementRoundNumber() {

    }
}