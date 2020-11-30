package hotciv.common;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.City;
import hotciv.framework.Player;

public class CityProxy implements City, ClientProxy {
    private final Requestor requestor;
    private final String ObjectId;

    public CityProxy(Requestor requestor, String objectId) {
        this.requestor = requestor;
        this.ObjectId = objectId;
    }

    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.CITY_GET_OWNER, Player.class);
    }

    public int getSize() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.CITY_GET_SIZE, Integer.class);
    }

    public int getTreasury() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.CITY_GET_TREASURY, Integer.class);
    }

    public String getProduction() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.CITY_GET_PRODUCTION, String.class);
    }

    public String getWorkforceFocus() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.CITY_GET_WORKFORCE_FOCUS, String.class);
    }

    public String getId() {
        return ObjectId;
    }
}
