package bowling;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    Game game;

    @Mock
    GameStatus gameStatus;

    @Before
    public void setUp() {
        game = new Game(gameStatus);
    }

    @Test
    public void rollsAreStoredInStatus() {
        final int points = 5;
        game.roll(points);
        verify(gameStatus).addRoll(eq(points));
    }

    @Test
    public void hasMoreRolls() {
        when(gameStatus.getRolls()).thenReturn(1);
        when(gameStatus.getBonusRolls()).thenReturn(0);
        when(gameStatus.getCurrentTurnPoints()).thenReturn(5);
        assertTrue(game.hasMoreRolls());
    }

    @Test
    public void hasOneRollOnStrike() {
        when(gameStatus.getRolls()).thenReturn(1);
        when(gameStatus.getBonusRolls()).thenReturn(0);
        when(gameStatus.getCurrentTurnPoints()).thenReturn(10);
        assertFalse(game.hasMoreRolls());
    }

    @Test
    public void hasTenTurns() {
        when(gameStatus.getRolls()).thenReturn(20);
        when(gameStatus.getBonusRolls()).thenReturn(0);
        assertFalse(game.hasMoreRolls());
    }

    @Test
    public void gameFinishedAfter10Turns() {
        when(gameStatus.getRolls()).thenReturn(20);
        when(gameStatus.getBonusRolls()).thenReturn(0);
        game.nextRound();
        assertTrue(game.isGameFinished());
    }

    @Test
    public void hasOneRollBonusOnSpareLast() {
        when(gameStatus.getRolls()).thenReturn(20);
        when(gameStatus.isSpare()).thenReturn(true);
        game.roll(1);
        verify(gameStatus).setBonusRolls(eq(1));
    }

    @Test
    public void hasTwoRollsBonusOnStrikeLast() {
        when(gameStatus.getRolls()).thenReturn(20);
        when(gameStatus.isStrike()).thenReturn(true);
        game.roll(1);
        verify(gameStatus).setBonusRolls(eq(2));
    }

}