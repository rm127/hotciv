package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

import java.util.UUID;

public class StubUnit3 implements Unit, Servant {
    private final Player owner;
    private final String type;
    private String ObjectId;

    public StubUnit3(Player owner, String type) {
        this.owner = owner;
        this.type = type;
        this.ObjectId = UUID.randomUUID().toString();
    }

    public String getTypeString() {
        return type;
    }

    public Player getOwner() {
        return owner;
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

    public String getId() {
        return ObjectId;
    }
}
