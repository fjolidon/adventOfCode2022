package aoc2022.day02;

import java.util.HashMap;
import java.util.Map;


public enum Shape {
    ROCK(1, 'A', 'X'),
    PAPER(2, 'B', 'Y'),
    SCISSOR(3, 'C', 'Z');

    private final int score;
    private final char strategyOpponent; // what the opponent will play
    private final char strategySelf; // what you should play (first part only)

    private final static Map<Character, Shape> findOpponentShape = new HashMap<>();
    private final static Map<Character, Shape> findSelfShape = new HashMap<>();
    private final static Map<Integer, Shape> getFromScore = new HashMap<>();

    static {
        for (Shape shape : values()) {
            findOpponentShape.put(shape.getStrategyOpponent(), shape);
            findSelfShape.put(shape.getStrategySelf(), shape);
            getFromScore.put(shape.getScore(), shape);
        }
    }

    Shape(int score, char strategyOpponent, char strategySelf) {
        this.score = score;
        this.strategyOpponent = strategyOpponent;
        this.strategySelf = strategySelf;
    }

    public int getScore() {
        return score;
    }

    public char getStrategyOpponent() {
        return strategyOpponent;
    }

    public char getStrategySelf() {
        return strategySelf;
    }

    public static Shape get(int score) {
        return getFromScore.get(score);
    }

    public int scoreAgainst(Shape that) {
        int scoreFromMatchup = Result.get(this, that).getScore();
        return scoreFromMatchup + score;
    }

    public static int getScoreForLinePartA(String line) {
        Shape opponent = findOpponentShape.get(line.charAt(0));
        Shape self = findSelfShape.get(line.charAt(2));

        return self.scoreAgainst(opponent);
    }

    public static int getScoreForLinePartB(String line) {
        Shape opponent = findOpponentShape.get(line.charAt(0));
        Result result = Result.get(line.charAt(2));
        Shape self = result.get(opponent);

        return self.scoreAgainst(opponent);
    }
}
