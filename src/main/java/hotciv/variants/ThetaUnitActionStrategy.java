package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitActionStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class ThetaUnitActionStrategy implements UnitActionStrategy {
    private static final String CARAVAN = "caravan";
    private static UnitActionStrategy baseStrategy;

    public ThetaUnitActionStrategy() {
        baseStrategy = new GammaUnitActionStrategy();
    }

    public void performAction(Position position, Game game) {
        String unitType = game.getUnitAt(position).getTypeString();

        if (unitType.equals(CARAVAN)) {
            // if the caravan isn't located in a city, do nothing
            if (game.getCityAt(position) == null) {
                return;
            }
            ((CityImpl) game.getCityAt(position)).increaseSize();
            ((CityImpl) game.getCityAt(position)).increaseSize();
            ((GameImpl) game).removeUnitAt(position);
            return;
        }
        baseStrategy.performAction(position, game);
    }
}
