package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.GameConstants.LEGION;

public class CityImpl implements City {
    private final Player owner;
    private int treasury = 0;

    CityImpl(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public int getSize() {
        return 1;
    }

    public int getTreasury() { return treasury; }

    public String getProduction() {
        return LEGION;
    }

    public String getWorkforceFocus() {
        return null;
    }

    public void increaseTreasury() {
        treasury += 6;
    }

}