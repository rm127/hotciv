package hotciv.variants;

import hotciv.common.UnitActionStrategy;
import org.junit.jupiter.api.BeforeEach;

public class TestGammaUnitActionStrategy {
    UnitActionStrategy uas;
//    Map<Position, Unit> unitMap = new HashMap<>();
//    Map<Position, City> cityMap = new HashMap<>();

    @BeforeEach
    public void setUp() {
        uas = new GammaUnitActionStrategy();
    }

//    @Test
//    void SettlersActionFoundsCity() {
//        Position position = new Position(0,0);
//        uas.performAction(position, );
//        assertThat(cityMap.get(position), is(notNullValue()));
//    }
}

/*
Test-list
- Settlers can found a city when performing action.
- Settler unit itself is removed after performing action
- Settler's founded city is owned by player who owned the unit
- Archers' defense doubles when performing action
- Archers' cannot move while fortified (performing action)
- If an archer fortifies while already fortified, it removes the fortification
 */