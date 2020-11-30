package hotciv.common;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.tool.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class HotCivClient {
    Game game;

    public static void main(String[] args) {
        HotCivClient client = new HotCivClient();

        switch (args[0]) {
            case "test":
                client.basicTest();
                break;
            case "story":
                client.storyTest();
                break;
        }

    }

    public HotCivClient() {
        ClientRequestHandler crh = new SocketClientRequestHandler();
        crh.setServer("localhost", 1234);

        Requestor requestor = new StandardJSONRequestor(crh);

        game = new GameProxy(requestor);

        DrawingEditor editor = new MiniDrawApplication( "Client", new HotCivFactory4(game));
        editor.open();
        editor.setTool(new CompositionTool(editor, game));
    }

    private void basicTest() {
        game.endOfTurn();
        game.getPlayerInTurn();
        game.getAge();
    }

    private void storyTest() {
        System.out.println(" === TESTING SIMPLE METHODS ===");
        System.out.println(" - Age:                             " + game.getAge());
        System.out.println(" - Winner:                          " + game.getWinner());
        System.out.println(" - PlayerInTurn:                    " + game.getPlayerInTurn());
        System.out.println(" ---------------- end of turn ----------------"); game.endOfTurn();
        System.out.println(" - PlayerInTurn:                    " + game.getPlayerInTurn());
        System.out.println(" - Move (4,4) -> (4,3):             " + game.moveUnit(new Position(4,4), new Position(4,3)));
        System.out.println(" ---------------- end of turn ----------------"); game.endOfTurn();
        System.out.println(" - Age:                             " + game.getAge());
        System.out.println(" - GetTileAt (3,5):                 " + game.getTileAt(new Position(3,5)).getTypeString());
        System.out.println(" - GetTileAt (0,0):                 " + game.getTileAt(new Position(0,0)).getTypeString());
    }
}
