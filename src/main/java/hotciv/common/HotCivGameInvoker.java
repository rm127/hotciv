package hotciv.common;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.stub.StubCity3;
import hotciv.stub.StubUnit3;
import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;

import javax.servlet.http.HttpServletResponse;

public class HotCivGameInvoker implements Invoker {
    private final Game servant;
    private final Gson gson;
    private final NameService nameService;
    JsonParser jsonParser = new JsonParser();

    public HotCivGameInvoker(Game servant, NameService nameService, Gson gson) {
        this.servant = servant;
        this.gson = gson;
        this.nameService = nameService;

    }

    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        JsonArray params = jsonParser.parse(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply;

        try {
            String operationName = requestObject.getOperationName();
            switch (operationName) {

                case OperationNames.GAME_GET_UNIT_AT:
                    Position pos5 = new Position(gson.fromJson(params.get(0), Integer.class), gson.fromJson(params.get(1), Integer.class));
                    StubUnit3 unit = (StubUnit3) servant.getUnitAt(pos5);
                    String response = null;
                    if (unit != null) {
                        nameService.storeUnit(unit.getId(), unit);
                        response = gson.toJson(unit.getId());
                    }
                    reply = new ReplyObject(HttpServletResponse.SC_OK, response);
                    break;

                case OperationNames.GAME_GET_CITY_AT:
                    Position pos4 = new Position(gson.fromJson(params.get(0), Integer.class), gson.fromJson(params.get(1), Integer.class));
                    StubCity3 city = (StubCity3) servant.getCityAt(pos4);
                    String response1 = null;
                    if (city != null) {
                        nameService.storeCity(city.getId(), city);
                        response1 = gson.toJson(city.getId());
                    }
                    reply = new ReplyObject(HttpServletResponse.SC_OK, response1);
                    break;

                case OperationNames.GAME_GET_WINNER:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getWinner()));
                    break;

                case OperationNames.GAME_GET_AGE:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getAge()));
                    break;

                case OperationNames.GAME_GET_PLAYER_IN_TURN:
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getPlayerInTurn()));
                    break;

                case OperationNames.GAME_MOVE_UNIT:
                    Position from = new Position(gson.fromJson(params.get(0), Integer.class), gson.fromJson(params.get(1), Integer.class));
                    Position to = new Position(gson.fromJson(params.get(2), Integer.class), gson.fromJson(params.get(3), Integer.class));
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.moveUnit(from, to)));
                    break;

                case OperationNames.GAME_END_OF_TURN:
                    servant.endOfTurn();
                    reply = new ReplyObject(HttpServletResponse.SC_OK, null);
                    break;

                case OperationNames.GAME_GET_TILE_AT:
                    Position pos = new Position(gson.fromJson(params.get(0), Integer.class), gson.fromJson(params.get(1), Integer.class));
                    reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getTileAt(pos).getTypeString()));
                    break;

                case OperationNames.GAME_CHANGE_WORKFORCE_FOCUS_IN_CITY_AT:
                    Position pos1 = new Position(gson.fromJson(params.get(0), Integer.class), gson.fromJson(params.get(1), Integer.class));
                    servant.changeWorkForceFocusInCityAt(pos1, gson.fromJson(params.get(2), String.class));
                    reply = new ReplyObject(HttpServletResponse.SC_OK, null);
                    break;

                case OperationNames.GAME_CHANGE_PRODUCTION_IN_CITY_AT:
                    Position pos2 = new Position(gson.fromJson(params.get(0), Integer.class), gson.fromJson(params.get(1), Integer.class));
                    servant.changeProductionInCityAt(pos2, gson.fromJson(params.get(2), String.class));
                    reply = new ReplyObject(HttpServletResponse.SC_OK, null);
                    break;

                case OperationNames.GAME_PERFORM_UNIT_ACTION_AT:
                    Position pos3 = new Position(gson.fromJson(params.get(0), Integer.class), gson.fromJson(params.get(1), Integer.class));
                    servant.performUnitActionAt(pos3);
                    reply = new ReplyObject(HttpServletResponse.SC_OK, null);
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
