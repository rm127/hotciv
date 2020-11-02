package hotciv.common;

import hotciv.framework.*;
import hotciv.variants.AlphaGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.GameConstants.SETTLER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestLoggingGame {
    private Game game;
    private Game logger;

    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaGameFactory());
        logger = new LoggingGame(game);
    }

    // Red wins at year 3000 BC and winner is not found until game is over
    @Test
    public void redWinsAtYear3000BC() {
        logger.getWinner();
        while (logger.getAge() < -3000) {
            skipOtherPlayersTurn();
        }
        logger.getWinner();
    }

    // The two players (red and blue) alternates taking turns
    @Test
    public void playersShouldAlternateTakingTurns() {
        logger.endOfTurn();
        logger.endOfTurn();
        logger.endOfTurn();
    }

    // Units can be moved
    @Test
    public void unitsCanBeMoved() {
        logger.moveUnit(new Position(2,0), new Position(2,1));
        game.endOfTurn();
        logger.moveUnit(new Position(3,2), new Position(3,3));
    }

    // It is possible to change the production of a city
    @Test
    public void possibleToChangeProductionInCity() {
        Position cityPosition = new Position(1,1);
        logger.changeProductionInCityAt(cityPosition, LEGION);
        logger.changeProductionInCityAt(cityPosition, SETTLER);
    }

    @Test
    public void performsUnitAction() {
        logger.performUnitActionAt(new Position(2,0));
        logger.performUnitActionAt(new Position(3,2));
    }

    @Test
    public void changesWorkforceFocus() {
        logger.changeWorkForceFocusInCityAt(new Position(1,1), foodFocus);
        logger.changeWorkForceFocusInCityAt(new Position(1,1), productionFocus);
    }



    private void skipOtherPlayersTurn() {
        game.endOfTurn();
        game.endOfTurn();
    }
}
