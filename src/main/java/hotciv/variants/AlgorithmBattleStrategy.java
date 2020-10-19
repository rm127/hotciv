package hotciv.variants;

import hotciv.common.BattleStrategy;
import hotciv.common.GameImpl;
import hotciv.common.ModifierStrategy;
import hotciv.common.Utilities;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;

import java.util.Iterator;

import static hotciv.framework.GameConstants.FOREST;
import static hotciv.framework.GameConstants.HILLS;

public class AlgorithmBattleStrategy implements BattleStrategy {
    private final ModifierStrategy modifierStrategy;

    public AlgorithmBattleStrategy(ModifierStrategy modifierStrategy) {
        this.modifierStrategy = modifierStrategy;
    }

    public boolean executeBattle(GameImpl game, Position attackerPos, Position defenderPos) {
        return calculateAttackingStrength(game, attackerPos) * modifierStrategy.getModifier() > calculateDefensiveStrength(game, defenderPos) * modifierStrategy.getModifier();
    }

    public int calculateAttackingStrength(Game game, Position p) {
        return calculateUnitStrength(game, p, game.getUnitAt(p).getAttackingStrength());
    }
    public int calculateDefensiveStrength(Game game, Position p) {
        return calculateUnitStrength(game, p, game.getUnitAt(p).getDefensiveStrength());
    }


    private int calculateUnitStrength(Game game, Position p, int unitStrength) {
        int terrainFactor = 1;
        if (game.getCityAt(p) != null) {
            terrainFactor = 3;
        }
        String tileType = game.getTileAt(p).getTypeString();
        if (tileType.equals(HILLS) || tileType.equals(FOREST)) {
            terrainFactor = 2;
        }

        int adjacentUnitSupport = 0;
        Iterator<Position> adjacentPositions = Utilities.getAdjacentPositions(p);
        while (adjacentPositions.hasNext()) {
            Position position = adjacentPositions.next();
            Unit unit = game.getUnitAt(position);
            // if a unit exists and belongs to the same owner as the unit in question
            if (unit != null && unit.getOwner() == game.getUnitAt(p).getOwner()) {
                adjacentUnitSupport++;
            }
        }
        return (unitStrength + adjacentUnitSupport) * terrainFactor;
    }

}
