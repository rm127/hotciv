package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class StubUnit3 implements Unit, Servant {
    public String getTypeString() {
        return GameConstants.LEGION;
    }

    public Player getOwner() {
        return Player.YELLOW;
    }

    public int getMoveCount() {
        return 1;
    }

    public int getDefensiveStrength() {
        return 4;
    }

    public int getAttackingStrength() {
        return 10;
    }
}
