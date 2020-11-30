package hotciv.common;

import frds.broker.*;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.StubGame3;
import hotciv.stub.StubUnit3;
import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestUnitBroker {
    private Unit unit;
    private StubGame3 servant;

    /**
     * Fixture for Broker testing.
     */
    @BeforeEach
    public void setUp() {
        servant = new StubGame3();

        Invoker invoker = new HotCivGeneralInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        unit = new GameProxy(requestor).getUnitAt(new Position(8, 9));
    }

    @Test
    void shouldReturnTypeLegion() {
        assertThat(unit.getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    void shouldReturnYellow() {
        assertThat(unit.getOwner(), is(Player.YELLOW));
    }

    @Test
    void shouldReturnMoveCount() {
        assertThat(unit.getMoveCount(), is(1));
    }

    @Test
    void shouldReturnDefensiveStrength() {
        assertThat(unit.getDefensiveStrength(), is(4));
    }

    @Test
    void shouldReturnAttackingStrength() {
        assertThat(unit.getAttackingStrength(), is(10));
    }
}