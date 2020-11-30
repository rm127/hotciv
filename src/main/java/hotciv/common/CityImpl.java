package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Player;

import java.util.UUID;

import static hotciv.framework.GameConstants.*;

public class CityImpl implements City {
    private final UnitStatStrategy unitStatStrategy;
    private Player owner;
    private int treasury = 0;
    private String currentlyProducing = LEGION;
    private int size = 1;
    private final String objectId = UUID.randomUUID().toString();

    public CityImpl(UnitStatStrategy unitStatStrategy, Player owner) {
        this.unitStatStrategy = unitStatStrategy;
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public int getSize() {
        return size;
    }

    public int getTreasury() { return treasury; }

    public String getProduction() {
        return currentlyProducing;
    }

    public String getWorkforceFocus() {
        return productionFocus;
    }

    public String getId() {
        return objectId;
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

    public void increaseSize() {
        size++;
    }
}