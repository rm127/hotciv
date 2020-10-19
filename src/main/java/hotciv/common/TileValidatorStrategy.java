package hotciv.common;

/**
 * The strategy for checking if a tile can be traversed by a given unit type.
 * Pattern used: Strategy.
 */
public interface TileValidatorStrategy {
    /**
     * Checks whether a unit of a given type can move to a given terrain.
     * @param tileType the type of the tile.
     * @param unitType the type of the unit.
     * @return whether the unit can move to this tile or not.
     */
    boolean canMoveHere(String tileType, String unitType);
}
