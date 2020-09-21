package hotciv.variants;

import hotciv.common.GameAgingStrategy;

import org.junit.jupiter.api.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestProgressiveGameAgingStrategy {
    GameAgingStrategy gas;

    @BeforeEach
    public void setUp() {
        gas = new ProgressiveGameAgingStrategy();
    }

    // Between 4000BC and 100BC, 100 years pass per round
    @Test
    void between4000bcAnd100bc100YearsPassPerRound() {
        assertThat(gas.calculateAgeIncrease(-4000), is(100));
        assertThat(gas.calculateAgeIncrease(-1100), is(100));
        assertThat(gas.calculateAgeIncrease(-200), is(100));
    }

    // Around birth of Christ the sequence is -100, -1, +1, +50
    @Test
    void aroundBirthOfChristTheSequenceVaries() {
        assertThat(gas.calculateAgeIncrease(-100), is(99));
        assertThat(gas.calculateAgeIncrease(-1), is(2));
        assertThat(gas.calculateAgeIncrease(1), is(49));
    }

    // Between 50AD and 1750, 50 years pass per round.
    @Test
    void between50ADAnd1750AD50YearsPassPerRound() {
        assertThat(gas.calculateAgeIncrease(50), is(50));
        assertThat(gas.calculateAgeIncrease(1000), is(50));
        assertThat(gas.calculateAgeIncrease(1700), is(50));
    }

    // Between 1750 and 1900, 25 years pass per round.
    @Test
    void between1750ADAnd1900AD25YearsPassPerRound() {
        assertThat(gas.calculateAgeIncrease(1750), is(25));
        assertThat(gas.calculateAgeIncrease(1800), is(25));
        assertThat(gas.calculateAgeIncrease(1875), is(25));
    }

    // Between 1900 and 1970, 5 years pass per round
    @Test
    void between1900ADAnd1970AD5YearsPassPerRound() {
        assertThat(gas.calculateAgeIncrease(1900), is(5));
        assertThat(gas.calculateAgeIncrease(1930), is(5));
        assertThat(gas.calculateAgeIncrease(1965), is(5));
    }

    // After 1970 1 year per round.
    @Test
    void after1970AD1YearPassPerRound() {
        assertThat(gas.calculateAgeIncrease(1970), is(1));
        assertThat(gas.calculateAgeIncrease(2000), is(1));
        assertThat(gas.calculateAgeIncrease(2150), is(1));
    }
}


/*
Test-list
    - Between 4000BC and 100BC 100 years pass per round.
    - Around birth of Christ the sequence is -100, -1, +1, +50.
    - Between 50AD and 1750 50 years pass per round.
    - Between 1750 and 1900 25 years pass per round.
    - Between 1900 and 1970 5 years per round.
    - After 1970 1 year per round.
 */