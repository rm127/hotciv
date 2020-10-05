package hotciv.variants;

import hotciv.common.GameImpl;
import hotciv.common.GameWinStrategy;
import hotciv.framework.Player;

public class AlternatingGameWinStrategy implements GameWinStrategy {
    GameWinStrategy beforeRound21Strategy;
    GameWinStrategy afterRound20Strategy;
    GameWinStrategy currentState;


    public AlternatingGameWinStrategy(GameWinStrategy beforeRound21Strategy, GameWinStrategy afterRound20Strategy) {
        this.beforeRound21Strategy = beforeRound21Strategy;
        this.afterRound20Strategy = afterRound20Strategy;
        this.currentState = null;
    }

    public Player getWinner(GameImpl game) {
        if (game.getCurrentRound() <= 20) {
            currentState = beforeRound21Strategy;
        } else {
            currentState = afterRound20Strategy;
        }
        // TODO: Start counting battle wins after round 20
        return currentState.getWinner(game);
    }

    public void incrementBattleWon(Player p) {

    }

    public void incrementRoundNumber() {

    }
}
