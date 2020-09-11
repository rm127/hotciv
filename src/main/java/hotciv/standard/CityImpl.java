package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.GameConstants.LEGION;
import static hotciv.framework.GameConstants.productionFocus;

public class CityImpl implements City {
    private Player owner;
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
        return productionFocus;
    }

    public void increaseTreasury() {
        treasury += 6;
    }

    public void setProduction(String unitType) {
        this.currentlyProducing = unitType;
    }

    public void changeOwner(Player owner) {
        this.owner = owner;
    }
}