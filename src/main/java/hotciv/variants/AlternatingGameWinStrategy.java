package hotciv.variants;

import hotciv.common.GameWinStrategy;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.HashMap;

public class AlternatingGameWinStrategy implements GameWinStrategy {
    GameWinStrategy beforeRound21Strategy;
    GameWinStrategy afterRound20Strategy;
    GameWinStrategy currentState;

    public AlternatingGameWinStrategy(GameWinStrategy beforeRound21Strategy, GameWinStrategy afterRound20Strategy) {
        this.beforeRound21Strategy = beforeRound21Strategy;
        this.afterRound20Strategy = afterRound20Strategy;
        this.currentState = null;
    }

    // TODO: Should we unit test this? Since it uses two already tested parts
    // TODO: Find out how to test if we only use single parameter "GameImpl"
    public Player getWinner(int gameAge, HashMap<Position, City> cityMap, HashMap<Player, Integer> playerBattleStats, int currentRound) {
        if (currentRound <= 20) {
            currentState = beforeRound21Strategy;
        } else {
            currentState = afterRound20Strategy;
        }
        return currentState.getWinner(gameAge, cityMap, playerBattleStats, currentRound);
    }
}
