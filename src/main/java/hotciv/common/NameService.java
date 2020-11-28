package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Unit;

public interface NameService {
    void storeCity(String id, City city);

    City getCity(String id);

    void storeUnit(String id, Unit unit);

    Unit getUnit(String id);
}
