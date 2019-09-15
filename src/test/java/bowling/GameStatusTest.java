package bowling;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static bowling.Game.PINS;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameStatusTest {

    GameStatus gameStatus;

    @Mock
    Points points;

    @Before
    public void setUp() {
        gameStatus = new GameStatus(points, PINS);
    }

    @Test
    public void addRollAddsPoints() {
        gameStatus.addRoll(1);
        verify(points).addPoint(eq(1));
    }

    @Test
    public void addRollAddsPointsStrike() {
        gameStatus.addRoll(10);
        verify(points).addPointStrike(eq(10));
    }

    @Test
    public void isStrike() {
        gameStatus.addRoll(10);
        assertTrue(gameStatus.isStrike());
    }

    @Test
    public void isSpare() {
        gameStatus.addRoll(5);
        gameStatus.addRoll(5);
        assertTrue(gameStatus.isSpare());
    }

    @Test
    public void rollsIncreaseWhenAddingRoll() {
        assertEquals(0, gameStatus.getRolls());
        gameStatus.addRoll(5);
        assertEquals(1, gameStatus.getRolls());
    }

    @Test
    public void nextRoundResetsCurrentPoints() {
        gameStatus.addRoll(5);
        assertEquals(5, gameStatus.getCurrentTurnPoints());
        gameStatus.nextRound();
        assertEquals(0, gameStatus.getCurrentTurnPoints());
    }

    @Test
    public void nextRoundIncreasesRollOnStrike() {
        gameStatus.addRoll(10);
        assertEquals(1, gameStatus.getRolls());
        gameStatus.nextRound();
        assertEquals(2, gameStatus.getRolls());
    }
}
