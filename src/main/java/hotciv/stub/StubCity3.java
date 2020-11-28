package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

import java.util.UUID;

public class StubCity3 implements City, Servant {
    Player owner;
    int size;
    String ObjectId = UUID.randomUUID().toString();


    public StubCity3(Player owner, int size) {
        this.owner = owner;
        this.size = size;
    }

    public Player getOwner() {
        return owner;
    }

    public int getSize() {
        return size;
    }

    public int getTreasury() {
        return 4;
    }

    public String getProduction() {
        return GameConstants.ARCHER;
    }

    public String getWorkforceFocus() {
        return GameConstants.productionFocus;
    }

    public String getId() {
        return ObjectId;
    }
}

