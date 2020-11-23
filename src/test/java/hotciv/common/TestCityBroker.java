package hotciv.common;

import frds.broker.*;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.stub.StubCity3;
import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCityBroker {
    private City city;
    private StubCity3 servant;

    /**
     * Fixture for Broker testing.
     */
    @BeforeEach
    public void setUp() {
        servant = new StubCity3();

        Invoker invoker = new HotCivCityInvoker(servant);

        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

        Requestor requestor = new StandardJSONRequestor(crh);

        city = new CityProxy(requestor);
    }

    @Test
    void shouldReturnGreen() {
        assertThat(city.getOwner(), is(Player.GREEN));
    }

    @Test
    void shouldReturnSize() {
        assertThat(city.getSize(), is(2));
    }

    @Test
    void shouldReturnTreasury() {
        assertThat(city.getTreasury(), is(4));
    }

    @Test
    void shouldReturnProduction() {
        assertThat(city.getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    void shouldReturnWorkForceFocus() {
        assertThat(city.getWorkforceFocus(), is(GameConstants.productionFocus));
    }
}
