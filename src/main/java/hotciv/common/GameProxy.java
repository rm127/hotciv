package hotciv.common;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;

import java.util.ArrayList;
import java.util.List;

public class GameProxy implements Game, ClientProxy {
    private final Requestor requestor;
    private final String ObjectId = "SINGLETON";
    private final List<GameObserver> observers;

    public GameProxy(Requestor requestor) {
        this.requestor = requestor;
        observers = new ArrayList<>();
    }

    public Tile getTileAt(Position p) {
        return new TileImpl(requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_GET_TILE_AT, String.class, p.getRow(), p.getColumn()));
    }

    public Unit getUnitAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_GET_UNIT_AT, String.class, p.getRow(), p.getColumn());
        if (id != null) return new UnitProxy(requestor, id);
        return null;
    }

    public City getCityAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_GET_CITY_AT, String.class, p.getRow(), p.getColumn());
        if (id != null) return new CityProxy(requestor, id);
        return null;
    }

    public Player getPlayerInTurn() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_GET_PLAYER_IN_TURN, Player.class);
    }

    public Player getWinner() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_GET_WINNER, Player.class);
    }

    public int getAge() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_GET_AGE, Integer.class);
    }

    public boolean moveUnit(Position from, Position to) {
        worldChangedAt(to);
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_MOVE_UNIT, Boolean.class,
                from.getRow(), from.getColumn(), to.getRow(), to.getColumn());
    }

    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_END_OF_TURN, Void.class);
        // observers.forEach(observer -> observer.turnEnds(currentPlayer, gameAge));
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_CHANGE_WORKFORCE_FOCUS_IN_CITY_AT, Void.class, p.getRow(), p.getColumn(), balance);
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_CHANGE_PRODUCTION_IN_CITY_AT, Void.class, p.getRow(), p.getColumn(), unitType);
    }

    public void performUnitActionAt(Position p) {
        requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.GAME_PERFORM_UNIT_ACTION_AT, Void.class, p.getRow(), p.getColumn());
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void setTileFocus(Position position) {

    }

    public void worldChangedAt(Position pos) {
        observers.forEach(observer -> observer.worldChangedAt(pos));
    }
}
