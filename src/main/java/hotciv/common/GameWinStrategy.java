package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.HashMap;

/**
 * The strategy for deciding who has won the game.
 * Pattern used: Strategy.
 */
public interface GameWinStrategy {
    /**
     * Returns the Player who has won the game if there is a winner.
     * @return the Player who has won the game.
     * @param gameAge the age of the game.
     */
    Player getWinner(int gameAge);
}
