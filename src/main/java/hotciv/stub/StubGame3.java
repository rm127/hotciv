package hotciv.stub;

import frds.broker.Servant;
import hotciv.common.TileImpl;
import hotciv.framework.*;

public class StubGame3 implements Game, Servant {
    private Player playerInTurn = Player.YELLOW;
    private String workforceFocus = GameConstants.productionFocus;
    private String cityProduction = GameConstants.SETTLER;
    private boolean unitActionPerformed = false;

    public Tile getTileAt(Position p) {
        if (p.getColumn() == 3 && p.getRow() == 3) return new TileImpl(GameConstants.PLAINS);
        return new TileImpl(GameConstants.OCEANS);
    }

    public Unit getUnitAt(Position p) {
        return null;
    }

    public City getCityAt(Position p) {
        return null;
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return Player.GREEN;
    }

    public int getAge() {
        return 1234;
    }

    public boolean moveUnit(Position from, Position to) {
        return true;
    }

    public void endOfTurn() {
        if (playerInTurn == Player.YELLOW) {
            playerInTurn = Player.GREEN;
        } else {
            playerInTurn = Player.YELLOW;
        }
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        workforceFocus = balance;
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        cityProduction = unitType;
    }

    public void performUnitActionAt(Position p) {
        unitActionPerformed = true;
    }

    public void addObserver(GameObserver observer) {

    }

    public void setTileFocus(Position position) {

    }

    public String getWorkForceFocus() {
        return workforceFocus;
    }

    public String getCityProduction() {
        return cityProduction;
    }

    public boolean isUnitActionPerformed() {
        return unitActionPerformed;
    }
}

