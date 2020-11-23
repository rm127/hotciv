package hotciv.variants;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;

public class LocalMethodClientRequestHandler implements ClientRequestHandler {
    private final Invoker invoker;

    public LocalMethodClientRequestHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    public String sendToServerAndAwaitReply(String request) {
        return invoker.handleRequest(request);
    }

    public void setServer(String hostname, int port) {

    }

    public void close() {

    }
}
