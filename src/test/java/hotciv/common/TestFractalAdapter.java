package hotciv.common;

import hotciv.framework.*;

import hotciv.stubs.TestFractalAdapterGameFactory;
import org.junit.jupiter.api.*;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestFractalAdapter {
    @Test
    void testFractalMap() {
        HashSet<String> tiles = new HashSet<>();

        for (int i = 0; i < 25; i++) {
            Game game = new GameImpl(new TestFractalAdapterGameFactory());

            tiles.add(game.getTileAt(new Position(0, 0)).getTypeString());
        }

        Boolean wasAllTheSame = tiles.size() > 1;

        assertThat(wasAllTheSame, is(false));
    }
}