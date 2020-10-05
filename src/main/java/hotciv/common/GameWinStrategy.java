package hotciv.common;

import hotciv.framework.Player;

/**
 * The strategy for deciding who has won the game.
 * Pattern used: Strategy.
 */
public interface GameWinStrategy {
    /**
     * Returns the Player who has won the game if there is a winner.
     * @return the Player who has won the game.
     * @param game the GameImpl instance.
     */
    Player getWinner(GameImpl game);

    /**
     * Increments the number of battles the player has won.
     * @param p The player in question.
     */
    void incrementBattleWon(Player p);

    /**
     * Increments the count of rounds played.
     */
    void incrementRoundNumber();
}
