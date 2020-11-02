package hotciv.common;

import hotciv.framework.*;

public class LoggingGame implements Game {
    private final Game game;

    public LoggingGame(Game game) {
        this.game = game;
    }

    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    public Player getWinner() {
        Player winner = game.getWinner();
        if (winner != null) {
            System.out.printf("%s has won the game %n", winner);
        } else {
            System.out.printf("no winner has been found yet %n");
        }
        return winner;
    }

    public int getAge() {
        return game.getAge();
    }

    public boolean moveUnit(Position from, Position to) {
        String unitType = game.getUnitAt(from).getTypeString();
        boolean result = game.moveUnit(from, to);
        if (result) {
            System.out.printf("%s moves %s from %s to %s %n",
                game.getPlayerInTurn(),
                unitType,
                from,
                to
            );
        }
        return result;
    }

    public void endOfTurn() {
        System.out.printf("%s ends their turn %n", game.getPlayerInTurn());
        game.endOfTurn();
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        System.out.printf("%s changes workforce focus in city at %s to %s %n",
            game.getPlayerInTurn(),
            p,
            balance
        );
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.printf("%s changes production in city at %s to %s %n",
            game.getPlayerInTurn(),
            p,
            unitType
        );
        game.changeProductionInCityAt(p, unitType);
    }

    public void performUnitActionAt(Position p) {
        System.out.printf("%s %s at %s performs its action %n",
            game.getPlayerInTurn(),
            getUnitAt(p).getTypeString(),
            p
        );
        game.performUnitActionAt(p);
    }
}
