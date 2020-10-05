package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.common.GameWinStrategy;
import hotciv.framework.Player;

public class AlternatingGameWinStrategy implements GameWinStrategy {
    GameWinStrategy beforeRound21Strategy;
    GameWinStrategy afterRound20Strategy;
    GameWinStrategy currentState;

    private int currentRound = 1;

    public AlternatingGameWinStrategy(GameWinStrategy beforeRound21Strategy, GameWinStrategy afterRound20Strategy) {
        this.beforeRound21Strategy = beforeRound21Strategy;
        this.afterRound20Strategy = afterRound20Strategy;
        this.currentState = beforeRound21Strategy;
    }

    public Player getWinner(GameImpl game) {
        checkState();
        return currentState.getWinner(game);
    }

    public void incrementBattleWon(Player p) {
        checkState();
        currentState.incrementBattleWon(p);
    }

    public void incrementRoundNumber() {
        currentRound++;
    }

    private void checkState() {
        if (currentRound > 20) {
            currentState = afterRound20Strategy;
        } else {
            currentState = beforeRound21Strategy;
        }
    }
}
