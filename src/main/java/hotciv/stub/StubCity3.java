package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class StubCity3 implements City, Servant {

    public Player getOwner() {
        return Player.GREEN;
    }

    public int getSize() {
        return 2;
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
}

