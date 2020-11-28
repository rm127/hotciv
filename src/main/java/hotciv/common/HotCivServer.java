package hotciv.common;

import frds.broker.Invoker;
import frds.broker.ServerRequestHandler;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.framework.Game;
import hotciv.stub.StubGame3;
import hotciv.variants.SemiGameFactory;
import hotciv.view.CivDrawing;
import hotciv.view.tool.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class HotCivServer {

    public static void main(String[] args) {
        new HotCivServer();
    }

    public HotCivServer() {
        Game servant = new GameImpl(new SemiGameFactory());

        Invoker invoker = new HotCivGeneralInvoker(servant);
        ServerRequestHandler crh = new SocketServerRequestHandler();

        crh.setPortAndInvoker(1234, invoker);

        crh.start();

//        DrawingEditor editor = new MiniDrawApplication( "Server", new HotCivFactory4(servant));
//        editor.open();
//        editor.setTool( new CompositionTool(editor, servant) );
    }
}
