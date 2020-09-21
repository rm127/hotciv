package hotciv.common;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String type;
    private int moveCount = 1;
    private int defensiveStrength = 3;
    private boolean isFortified = false;

    public UnitImpl(Player owner, String type) {
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
        return defensiveStrength;
    }

    public void setDefensiveStrength(int newStrength) {
        defensiveStrength = newStrength;
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

    public void setFortified(boolean fortified) {
        isFortified = fortified;
    }

    public boolean isFortified() {
        return isFortified;
    }
}
