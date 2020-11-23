package hotciv.variants;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class NullObserver implements GameObserver {
    public void worldChangedAt(Position pos) {

    }
    public void turnEnds(Player nextPlayer, int age) {

    }
    public void tileFocusChangedAt(Position position) {

    }
}
