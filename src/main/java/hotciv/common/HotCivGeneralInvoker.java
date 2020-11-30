package hotciv.common;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.RequestObject;
import hotciv.framework.Game;
import java.util.HashMap;

public class HotCivGeneralInvoker implements Invoker {
    private final Gson gson;
    private final Game game;
    private final NameService nameService;
    private final HashMap<String, Invoker> invokers;

    public HotCivGeneralInvoker(Game servant) {
        this.gson = new Gson();
        this.game = servant;
        this.nameService = new NameServiceImpl();
        this.invokers = new HashMap<>();

        // invokers
        invokers.put(OperationNames.TYPE_GAME, new HotCivGameInvoker(game, nameService, gson));
        invokers.put(OperationNames.TYPE_CITY, new HotCivCityInvoker(nameService, gson));
        invokers.put(OperationNames.TYPE_UNIT, new HotCivUnitInvoker(nameService, gson));
    }

    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        String operationName = requestObject.getOperationName();

        String prefix = operationName.split("@")[0];
        invokers.get(prefix);

        return invokers.get(prefix).handleRequest(request);
    }
}