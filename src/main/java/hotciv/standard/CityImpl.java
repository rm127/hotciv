package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.GameConstants.LEGION;

public class CityImpl implements City {
    private final Player owner;
    private int treasury = 0;
    private String currentlyProducing = LEGION;

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
        return currentlyProducing;
    }

    public String getWorkforceFocus() {
        return null;
    }

    public void increaseTreasury() {
        treasury += 6;
    }

    public void setProduction(String unitType) {
        this.currentlyProducing = unitType;
    }
}