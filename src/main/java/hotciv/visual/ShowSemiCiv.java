package hotciv.visual;

import hotciv.common.GameImpl;
import hotciv.framework.Game;
import hotciv.variants.SemiGameFactory;
import hotciv.view.tool.CompositionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class ShowSemiCiv {
    public static void main(String[] args) {
        Game game = new GameImpl(new SemiGameFactory());

        DrawingEditor editor =
                new MiniDrawApplication( "Move any unit using the mouse",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("SemiCiv here we goooo");
        editor.setTool( new CompositionTool(editor, game) );
    }
}
