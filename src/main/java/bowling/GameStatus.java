package bowling;

public class GameStatus {

    private int rolls;
    private int bonusRolls;
    private int currentTurnPoints;
    private int pins;

    private Points points;


    public GameStatus(Points points, int pins){
        this.rolls = 0;
        this.currentTurnPoints = 0;
        this.bonusRolls = 0;
        this.points = points;
        this.pins = pins;
    }

    public void addRoll(int points) {
        this.rolls += 1;
        this.currentTurnPoints += points;

        if(this.isStrike()){
            this.points.addPointStrike(points);
        } else {
            this.points.addPoint(points);
        }
    }

    public void nextRound(){
        if(isStrike()){
            this.rolls += 1;
        }
        this.currentTurnPoints = 0;
    }

    public boolean isStrike(){
        return this.rolls % 2 == 1 && this.currentTurnPoints == this.pins;
    }

    public boolean isSpare(){
        return this.rolls % 2 == 0 && this.currentTurnPoints == this.pins;
    }

    public int getRolls(){
        return rolls;
    }

    public int getCurrentTurnPoints(){
        return currentTurnPoints;
    }

    public void setBonusRolls(int rolls){
        this.bonusRolls = rolls;
    }

    public int getBonusRolls(){
        return this.bonusRolls;
    }
}
