package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.common.GameWinStrategy;
import hotciv.framework.Player;

public class AlternatingGameWinStrategy implements GameWinStrategy {
    GameWinStrategy afterRound20Strategy;
    GameWinStrategy currentState;

    private int currentRound = 1;

    public AlternatingGameWinStrategy(GameWinStrategy beforeRound21Strategy, GameWinStrategy afterRound20Strategy) {
        this.afterRound20Strategy = afterRound20Strategy;
        this.currentState = beforeRound21Strategy;
    }

    public Player getWinner(GameImpl game) {
        return currentState.getWinner(game);
    }

    public void incrementBattleWon(Player p) {
        currentState.incrementBattleWon(p);
    }

    public void incrementRoundNumber() {
        currentRound++;
        if (currentRound > 20) {
            currentState = afterRound20Strategy;
        }
    }
}
