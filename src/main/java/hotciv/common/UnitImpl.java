package hotciv.common;

import hotciv.framework.Player;
import hotciv.framework.Unit;

import static hotciv.framework.GameConstants.*;

public class UnitImpl implements Unit {

    private final Player owner;
    private final String type;
    private int moveCount = 1;
    private int defensiveStrength;
    private boolean isFortified = false;
    private int attackingStrength;

    public UnitImpl(Player owner, String type) {
        this.owner = owner;
        this.type = type;

        switch (type) {
            case ARCHER:
                defensiveStrength = 3;
                attackingStrength = 2;
                break;
            case LEGION:
                defensiveStrength = 2;
                attackingStrength = 4;
                break;
            case SETTLER:
                defensiveStrength = 3;
                attackingStrength = 0;
                break;
        }
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
        return attackingStrength;
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
