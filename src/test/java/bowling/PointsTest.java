package bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointsTest {

    Points points;

    @Before
    public void setUp() {
        points = new Points();
    }

    @Test
    public void getScoreWithNormalGame() {
        points.addPoint(5);
        points.addPoint(0);
        assertEquals(5, points.getScore());
    }

    @Test
    public void getScoreWithSpareGame() {
        points.addPoint(5);
        points.addPoint(5);
        points.addPoint(5);
        points.addPoint(0);
        assertEquals(20, points.getScore());
    }

    @Test
    public void getScoreWithStrikeGame() {
        points.addPointStrike(10);
        points.addPoint(5);
        points.addPoint(1);
        assertEquals(22, points.getScore());
    }
}