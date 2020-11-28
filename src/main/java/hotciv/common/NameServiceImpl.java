package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Unit;

import java.util.HashMap;

public class NameServiceImpl implements NameService {
    HashMap<String, City> cities;
    HashMap<String, Unit> units;

    public NameServiceImpl() {
        this.cities = new HashMap<>();
        this.units = new HashMap<>();
    }

    public void storeCity(String id, City city) {
        cities.put(id, city);
    }

    public City getCity(String id) {
        return cities.get(id);
    }

    public void storeUnit(String id, Unit unit) {
        units.put(id, unit);
    }

    public Unit getUnit(String id) {
        return units.get(id);
    }
}
