package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.GameConstants.*;

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

    public int getProductionPrice() {
        switch (currentlyProducing) {
            case ARCHER:
                return 10;
            case LEGION:
                return 15;
            case SETTLER:
                return 30;
            // not used due to preconditions
            default:
                return 0;
        }
    }

    public void changeOwner(Player owner) {
        this.owner = owner;
    }

    public void decreaseTreasury() {
        treasury -= getProductionPrice();
    }
}