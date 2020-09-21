package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitActionStrategy;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;

import java.util.Map;

import static hotciv.framework.GameConstants.SETTLER;

public class GammaUnitActionStrategy implements UnitActionStrategy {
    public void performAction(Position position, Game game) {
        Unit unit = game.getUnitAt(position);
        if (unit.getTypeString().equals(SETTLER)) {
            City city = new CityImpl(game.getPlayerInTurn());
            ((GameImpl) game).addCityAt(position, city);
        }

    }
}
