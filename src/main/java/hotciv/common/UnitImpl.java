package hotciv.common;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String type;
    private int moveCount = 1;

    UnitImpl(Player owner, String type) {
        this.owner = owner;
        this.type = type;
    }

    public String getTypeString() {
        return type;
    }

    public Player getOwner() {
        return owner;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getDefensiveStrength() {
        return 3;
    }

    public int getAttackingStrength() {
        return 0;
    }

    public void decreaseMoveCount() {
        moveCount -= 1;
    }

    public void resetMoveCount() {
        moveCount = 1;
    }
}
