package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitActionStrategy;
import hotciv.common.UnitImpl;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;

import java.util.Map;

import static hotciv.framework.GameConstants.ARCHER;
import static hotciv.framework.GameConstants.SETTLER;

public class GammaUnitActionStrategy implements UnitActionStrategy {
    public void performAction(Position position, Game game) {
        // The unit at the given position
        Unit unit = game.getUnitAt(position);

        // If the unit is not owned by the current player
        if (game.getPlayerInTurn() != unit.getOwner()) {
            return;
        }

        switch (unit.getTypeString()) {
            // If the unit is a settler
            case SETTLER:
                // Add a new city to the game
                City city = new CityImpl(game.getPlayerInTurn());
                ((GameImpl) game).addCityAt(position, city);
                // Remove the unit
                ((GameImpl) game).removeUnitAt(position);
            // If the unit is an archer
            case ARCHER:
                // Double its defense strength
                ((UnitImpl) unit).setDefensiveStrength(unit.getDefensiveStrength() * 2);
                // Get the current fortification state of the archer
                boolean isFortified = ((UnitImpl) unit).isFortified();
                // Set the fortification state to the opposite of the old state
                ((UnitImpl) unit).setFortified(!isFortified);
        }

    }
}
