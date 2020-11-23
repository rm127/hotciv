package hotciv.common;

import frds.broker.*;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.StubGame3;
import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGameBroker {
    private Game game;
    private StubGame3 servant;

    /**
     * Fixture for Broker testing.
     */
    @BeforeEach
    public void setUp() {
        servant = new StubGame3();
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);

        Invoker invoker = new HotCivGameInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        game = new GameProxy(requestor);
        game.addObserver(nullObserver);
    }

    @Test
    void shouldHaveWinner() {
        assertThat(game.getWinner(), is(Player.GREEN));
    }

    @Test
    void shouldGetAge() {
        assertThat(game.getAge(), is(1234));
    }

    @Test
    void shouldBeYellowPlayer() {
        assertThat(game.getPlayerInTurn(), is(Player.YELLOW));
    }

    @Test
    void shouldMoveUnit() {
        boolean movedUnit = game.moveUnit(new Position(1,1), new Position(1,2));
        assertThat(movedUnit, is(true));
    }

    @Test
    void shouldEndTurn() {
        assertThat(game.getPlayerInTurn(), is(Player.YELLOW));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.GREEN));
    }

    @Test
    void shouldBePlains() {
        assertThat(game.getTileAt(new Position(3,3)).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    void shouldChangeWorkForceFocus() {
        assertThat(servant.getWorkForceFocus(), is(GameConstants.productionFocus));
        game.changeWorkForceFocusInCityAt(new Position(1,1), GameConstants.foodFocus);
        assertThat(servant.getWorkForceFocus(), is(GameConstants.foodFocus));
    }

    @Test
    void shouldChangeCityProduction() {
        assertThat(servant.getCityProduction(), is(GameConstants.SETTLER));
        game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
        assertThat(servant.getCityProduction(), is(GameConstants.ARCHER));
    }

    @Test
    void shouldPerformUnitAction() {
        assertThat(servant.isUnitActionPerformed(), is(false));
        game.performUnitActionAt(new Position(1,1));
        assertThat(servant.isUnitActionPerformed(), is(true));
    }
}
