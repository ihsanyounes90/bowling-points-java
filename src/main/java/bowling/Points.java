package bowling;

import java.util.ArrayList;
import java.util.List;

import static bowling.Game.PINS;
import static bowling.Game.ROLLS;

public class Points {

    private List<Integer> points;

    public Points() {
        this.points = new ArrayList<Integer>();
    }

    public void addPoint(int points) {
        this.points.add(points);
    }

    public void addPointStrike(int points) {
        this.points.add(points);
        this.points.add(0);
    }

    public int getScore() {

        int score = 0;
        for (int i = 0; i < Math.min(points.size(), ROLLS); i += 2) {
            score += getRoundPoints(i);
        }
        return score;
    }

    private int getRoundPoints(int index) {
        int roundPoints = points.get(index) + points.get(index + 1);

        // Strike
        if (points.get(index) == PINS) {
            if (index + 2 < points.size()) {
                roundPoints += points.get(index + 2);
            }
            if (index + 3 < points.size()) {
                if (points.get(index + 2) == PINS && index + 4 < points.size()) {
                    roundPoints += points.get(index + 4);
                } else {
                    roundPoints += points.get(index + 3);
                }
            }

        } else {
            // spare
            if (roundPoints == PINS) {
                if (index + 2 < points.size()) {
                    roundPoints += points.get(index + 1);
                }
            }
        }
        return roundPoints;
    }
}
