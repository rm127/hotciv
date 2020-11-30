package hotciv.common;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.City;

import javax.servlet.http.HttpServletResponse;

public class HotCivCityInvoker implements Invoker {
    private final Gson gson;
    private final NameService nameService;

    public HotCivCityInvoker(NameService nameService, Gson gson) {
        this.gson = gson;
        this.nameService = nameService;
    }

    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        String objectId = requestObject.getObjectId();

        ReplyObject reply;

        City servant = nameService.getCity(objectId);

        try {
            String operationName = requestObject.getOperationName();
            switch (operationName) {
                case OperationNames.CITY_GET_OWNER:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getOwner()));
                    break;
                case OperationNames.CITY_GET_SIZE:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getSize()));
                    break;
                case OperationNames.CITY_GET_TREASURY:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getTreasury()));
                    break;
                case OperationNames.CITY_GET_PRODUCTION:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getProduction()));
                    break;
                case OperationNames.CITY_GET_WORKFORCE_FOCUS:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getWorkforceFocus()));
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
