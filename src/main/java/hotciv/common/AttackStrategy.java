package hotciv.common;

import hotciv.framework.Game;
import hotciv.framework.Position;

public interface AttackStrategy {
    void computeWinner(Game game, Position attacker, Position defender);
}
