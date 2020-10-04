package hotciv.common;

/**
 * The factory for all Civ variations.
 * Pattern used: Factory.
 */
public interface GameFactory {
    /**
     * Creates the GameAgingStrategy object.
     * @return a GameAgingStrategy object.
     */
    GameAgingStrategy createGameAgingStrategy();

    /**
     * Creates the GameWinStrategy object.
     * @return a GameWinStrategy object.
     */
    GameWinStrategy createGameWinStrategy();

    /**
     * Creates the UnitActionStrategy object.
     * @return a UnitActionStrategy object.
     */
    UnitActionStrategy createUnitActionStrategy();

    /**
     * Creates the WorldLayoutStrategy object.
     * @return a WorldLayoutStrategy object.
     */
    WorldLayoutStrategy createWorldLayoutStrategy();

    /**
     * Creates the BattleStrategy object.
     * @return a BattleStrategy object.
     */
    BattleStrategy createBattleStrategy();
}
