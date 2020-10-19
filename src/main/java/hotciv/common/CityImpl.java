package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.GameConstants.*;

public class CityImpl implements City {
    private final UnitStatStrategy unitStatStrategy;
    private Player owner;
    private int treasury = 0;
    private String currentlyProducing = LEGION;

    public CityImpl(UnitStatStrategy unitStatStrategy, Player owner) {
        this.unitStatStrategy = unitStatStrategy;
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
        return unitStatStrategy.getCost(currentlyProducing);
    }

    public void changeOwner(Player owner) {
        this.owner = owner;
    }

    public void decreaseTreasury() {
        treasury -= getProductionPrice();
    }
}