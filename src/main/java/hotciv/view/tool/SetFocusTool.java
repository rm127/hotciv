package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    // TODO: Kan vi fjerne editor variablen når vi ikke bruger den? Gælder alle Tools
    private final DrawingEditor editor;
    private final Game game;
    private Position downPosition;

    public SetFocusTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        downPosition = GfxConstants.getPositionFromXY(x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        // prevent selection if pressing down in one tile and letting go in another. Ref. HCI
        if (downPosition.equals(GfxConstants.getPositionFromXY(x, y))) {
            game.setTileFocus(downPosition);
        }
    }
}
