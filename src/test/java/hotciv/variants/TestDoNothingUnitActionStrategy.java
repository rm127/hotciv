package hotciv.variants;

import hotciv.common.GameAgingStrategy;

import hotciv.common.UnitActionStrategy;
import hotciv.framework.Position;
import org.junit.jupiter.api.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestDoNothingUnitActionStrategy {
    UnitActionStrategy uas;

    @BeforeEach
    public void setUp() {
        uas = new DoNothingUnitActionStrategy();
    }

    // Neither archers or settlers have any unit actions
//    @Test
//    void NoUnitActions() {
//        // There is a red archer at position 2,0
//        Position position = new Position(2, 0);
//        assertThat(uas.performAction(position, ););
//    }
}


/*
Test-list
    - No matter what year it is, the game should always progress 100 years at a time.
 */