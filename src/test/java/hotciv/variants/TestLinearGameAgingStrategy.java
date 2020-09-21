package hotciv.variants;

import hotciv.common.GameAgingStrategy;

import org.junit.jupiter.api.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestLinearGameAgingStrategy {
    GameAgingStrategy gas;

    @BeforeEach
    public void setUp() {
        gas = new LinearGameAgingStrategy();
    }

    // No matter what year it is, the game should always progress 100 years at a time.
    @Test
    void shouldAlwaysProgress100YearsAtATime() {
        assertThat(gas.calculateAgeIncrease(-4000), is(100));
        assertThat(gas.calculateAgeIncrease(0), is(100));
        assertThat(gas.calculateAgeIncrease(2100), is(100));
    }
}


/*
Test-list
    - No matter what year it is, the game should always progress 100 years at a time.
 */