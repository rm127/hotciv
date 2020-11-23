package hotciv.common;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitProxy implements Unit, ClientProxy {
    private final Requestor requestor;
    private final String ObjectId = "SINGLETON";

    public UnitProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.UNIT_GET_TYPE, String.class);
    }

    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.UNIT_GET_OWNER, Player.class);
    }

    public int getMoveCount() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.UNIT_GET_MOVE_COUNT, Integer.class);
    }

    public int getDefensiveStrength() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.UNIT_GET_DEFENSIVE_STRENGTH, Integer.class);
    }

    public int getAttackingStrength() {
        return requestor.sendRequestAndAwaitReply(ObjectId, OperationNames.UNIT_GET_ATTACKING_STRENGTH, Integer.class);
    }
}
