package bowling.acceptance;

import bowling.Game;
import bowling.GameStatus;
import bowling.Points;
import org.junit.Before;
import org.junit.Test;

import static bowling.Game.PINS;
import static org.junit.Assert.*;

public class PlayGameTest {
    Game game;
    GameStatus gameStatus;
    Points points;

    @Before
    public void setUp() {
        points = new Points();
        gameStatus = new GameStatus(points, PINS);
        game = new Game(gameStatus);
    }

    @Test
    public void hasBonus1RollIfLastIsSpare() {
        for (int i = 0; i < 10; i++) {
            game.roll(5);
            game.roll(5);
            game.nextRound();
        }

        assertTrue(game.hasMoreRolls());

        game.roll(5);
        game.nextRound();

        assertFalse(game.hasMoreRolls());
        assertTrue(game.isGameFinished());
        assertEquals(150, points.getScore());
    }

    @Test
    public void hasBonus2RollIfLastIsStrike() {
        for (int i = 0; i < 10; i++) {
            game.roll(10);
            game.nextRound();
        }

        assertTrue(game.hasMoreRolls());
        game.roll(10);
        assertTrue(game.hasMoreRolls());
        game.roll(10);

        game.nextRound();

        assertFalse(game.hasMoreRolls());
        assertTrue(game.isGameFinished());
        assertEquals(300, points.getScore());
    }

    @Test
    public void hasNoBonusIfLastTurnIsNormal() {
        for (int i = 0; i < 10; i++) {
            game.roll(9);
            game.roll(0);
            game.nextRound();
        }

        assertFalse(game.hasMoreRolls());
        assertTrue(game.isGameFinished());
        assertEquals(90, points.getScore());
    }
}
