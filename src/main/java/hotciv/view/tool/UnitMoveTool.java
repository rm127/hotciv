package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.framework.DrawingEditor;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

import static hotciv.view.GfxConstants.getPositionFromXY;

public class UnitMoveTool extends SelectionTool {
    private final DrawingEditor editor;
    private final Game game;
    private Position fromPosition;

    public UnitMoveTool(DrawingEditor editor, Game game) {
        super(editor);
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        fromPosition = getPositionFromXY(x, y);
        if (game.getUnitAt(fromPosition) != null) super.mouseDown(e, x, y);
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        super.mouseDrag(e, x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        game.moveUnit(fromPosition, getPositionFromXY(x, y));
        super.mouseUp(e, x, y);
    }

}
