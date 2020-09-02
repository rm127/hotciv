package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityImpl implements City {

    public Player getOwner() {
        return Player.RED;
    }

    public int getSize() {
        return 0;
    }

    public int getTreasury() {
        return 0;
    }

    public String getProduction() {
        return null;
    }

    public String getWorkforceFocus() {
        return null;
    }
}
