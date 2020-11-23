package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

import static hotciv.view.GfxConstants.REFRESH_BUTTON;

/** Template for the CompositionTool exercise (FRS 36.44).
 * Composition tool is basically a State Pattern, similar
 * to MiniDraw's SelectionTool. That is, upon mouse-down
 * it must determine what the user wants (from analyzing
 * what graphical elements have been clicked - unit?
 * city? tile? turn-shield? etc.) and then set its
 * internal tool state to the appropriate tool - and
 * then delegate the mouse down request to that tool.
 *
 * @author Henrik Bærbak Christensen, Aarhus University
 */
public class CompositionTool extends NullTool {
  private final DrawingEditor editor;
  private final Game game;

  private Tool state;

  private Position startPosition;

  public CompositionTool(DrawingEditor editor, Game game) {
    state = new NullTool();
    this.editor = editor;
    this.game = game;
    state = null;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    // Find the figure (if any) just below the mouse click position
    HotCivFigure figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);
    // Next determine the state of tool to use
    if (figureBelowClickPoint != null) {
      switch (figureBelowClickPoint.getTypeString()) {

        case GfxConstants.TURN_SHIELD_TYPE_STRING:
          state = new EndOfTurnTool(editor, game);
          break;

        case GfxConstants.UNIT_TYPE_STRING:
          if (e.isShiftDown()) {
            state = new ActionTool(editor, game);
            break;
          }

        default:
          state = new SetFocusTool(editor, game);
      }
    } else {
      state = new SetFocusTool(editor, game);
    }
    startPosition = GfxConstants.getPositionFromXY(x, y);
    // Finally, delegate to the selected state
    state.mouseDown(e, x, y);
  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y) {
    if (!startPosition.equals(GfxConstants.getPositionFromXY(x, y)) && !e.isShiftDown()) {
      state = new UnitMoveTool(editor, game, startPosition);
    }
    // Finally, delegate to the selected state
    state.mouseDrag(e, x, y);
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    state.mouseUp(e, x, y);
    // check if user clicks the refresh button
    HotCivFigure figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);
    if (figureBelowClickPoint != null && figureBelowClickPoint.getTypeString().equals(REFRESH_BUTTON)) editor.drawing().requestUpdate();
  }
}