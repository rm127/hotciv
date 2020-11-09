package hotciv.variants;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class SpyGameObserver implements GameObserver {
    private int worldChangedCount = 0;
    private int turnEndsCount = 0;
    private Position lastPosition = null;
    private Player lastPlayer = null;
    private int lastAge;

    public void worldChangedAt(Position pos) {
        lastPosition = pos;
        worldChangedCount++;
    }

    public void turnEnds(Player nextPlayer, int age) {
        lastPlayer = nextPlayer;
        lastAge = age;
        turnEndsCount++;
    }

    public void tileFocusChangedAt(Position position) {
        lastPosition = position;
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public int getWorldChangedCount() {
        return worldChangedCount;
    }

    public int getTurnEndsCount() {
        return turnEndsCount;
    }

    public Player getLastPlayer() {
        return lastPlayer;
    }

    public int getLastAge() {
        return lastAge;
    }
}
