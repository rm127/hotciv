package hotciv.stubs;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.AlphaWorldLayoutStrategy;

import java.util.HashMap;

public class TestGammaCivAlphaWorldLayoutStrategy extends AlphaWorldLayoutStrategy {
    private final GameImpl game;
    public TestGammaCivAlphaWorldLayoutStrategy(Game game) {
        super(game);
        this.game = (GameImpl) game;
    }

    public void createCities() {
        game.addCityAt(new Position(1,1), Player.RED);
        game.addCityAt(new Position(4,1), Player.BLUE);
        game.addCityAt(new Position(15,15), Player.RED);
    }
}
