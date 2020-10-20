package hotciv.common;

import hotciv.framework.Player;
import hotciv.framework.Unit;

import static hotciv.framework.GameConstants.*;

public class UnitImpl implements Unit {
    private final UnitStatStrategy unitStatStrategy;
    private final Player owner;
    private final String type;
    private boolean isFortified = false;
    private int defensiveStrength;
    private final int attackingStrength;
    private int moveCount;

    public UnitImpl(UnitStatStrategy unitStatStrategy, Player owner, String type) {
        this.unitStatStrategy = unitStatStrategy;
        this.owner = owner;
        this.type = type;
        this.defensiveStrength = unitStatStrategy.getDefenseStrength(type);
        this.attackingStrength = unitStatStrategy.getAttackingStrength(type);
        this.moveCount = unitStatStrategy.getMoveCount(type);
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
        moveCount = unitStatStrategy.getMoveCount(type);
    }

    public void setFortified(boolean fortified) {
        isFortified = fortified;
    }

    public boolean isFortified() {
        return isFortified;
    }
}
