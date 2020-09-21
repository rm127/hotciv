package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.HashMap;

/**
 * The strategy for delivering the starting layout of the game world.
 * Pattern used: Strategy.
 */
public interface WorldLayoutStrategy {
    /**
     * Returns a map of the cities in the world.
     * @return a map of cities.
     */
    HashMap<Position, City> getCityMap();

    /**
     * Returns a map of the units in the world.
     * @return a map of units.
     */
    HashMap<Position, Unit> getUnitMap();

    /**
     * Returns a map of the tiles in the world.
     * @return a map of tiles.
     */
    HashMap<Position, Tile> getTileMap();
}
