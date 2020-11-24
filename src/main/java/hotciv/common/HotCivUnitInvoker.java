package hotciv.common;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.City;
import hotciv.framework.Unit;

import javax.servlet.http.HttpServletResponse;

public class HotCivUnitInvoker implements Invoker {
    private final Unit servant;
    private final Gson gson;
    JsonParser jsonParser = new JsonParser();

    public HotCivUnitInvoker(Unit servant) {
        this.servant = servant;
        this.gson = new Gson();
    }

    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        // JsonArray params = jsonParser.parse(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply;

        try {
            String operationName = requestObject.getOperationName();
            switch (operationName) {
                case OperationNames.UNIT_GET_TYPE:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getTypeString()));
                    break;
                case OperationNames.UNIT_GET_OWNER:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getOwner()));
                    break;
                case OperationNames.UNIT_GET_MOVE_COUNT:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getMoveCount()));
                    break;
                case OperationNames.UNIT_GET_DEFENSIVE_STRENGTH:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getDefensiveStrength()));
                    break;
                case OperationNames.UNIT_GET_ATTACKING_STRENGTH:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getAttackingStrength()));
                    break;
                default:
                    reply = new ReplyObject(HttpServletResponse.SC_NOT_FOUND, "No method found for this request: " + operationName);
            }
        } catch(Error e) {
            reply = new ReplyObject(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }

        return gson.toJson(reply);
    }
}
