package hotciv.common;

import frds.broker.Invoker;
import frds.broker.ServerRequestHandler;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.framework.Game;
import hotciv.variants.SemiGameFactory;

public class HotCivServer {

    public static void main(String[] args) {
        new HotCivServer();
    }

    public HotCivServer() {
        Game servant = new GameImpl(new SemiGameFactory());

        Invoker invoker = new HotCivGameInvoker(servant);
        ServerRequestHandler crh = new SocketServerRequestHandler();

        crh.setPortAndInvoker(1234, invoker);

        crh.start();
    }
}
