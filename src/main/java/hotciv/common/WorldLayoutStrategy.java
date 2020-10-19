package hotciv.common;

import hotciv.framework.Position;
import hotciv.framework.Tile;

import java.util.HashMap;

/**
 * The strategy for delivering the starting layout of the game world.
 * Pattern used: Strategy.
 */
public interface WorldLayoutStrategy {
    /**
     * Adds the cities in the world to the game.
     */
    void createCities();

    /**
     * Adds the units in the world to the game.
     */
    void createUnits();

    /**
     * Returns a map of the tiles in the world.
     * @return a map of tiles.
     */
    HashMap<Position, Tile> getTileMap();
}
