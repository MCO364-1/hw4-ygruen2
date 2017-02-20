package mco364;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameOfLifeTest {

    @Test
    public void neighborCount() {
        GameOfLife game = new GameOfLife(1);
        game.nextGen();
        assertEquals(2, game.neighborCount(12, 12));
        assertEquals(1, game.neighborCount(12, 13));
    }
    
    @Test
    public void isAliveNextGeneration() {
        GameOfLife game = new GameOfLife(1);
        game.nextGen();
        assertTrue(game.isAliveNextGeneration(12, 12));
        assertFalse(game.isAliveNextGeneration(12, 13));
    }

}
