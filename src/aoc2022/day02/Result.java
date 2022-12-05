package aoc2022.day02;

import java.util.HashMap;
import java.util.Map;


public enum Result {
    LOSE(0, 'X'),
    DRAW(1, 'Y'),
    WIN(2, 'Z');

    private final int baseScore;
    private final static int SCORE_FACTOR = 3;
    private final char code; // only for second part


    private final static Map<Integer, Result> getFromScore = new HashMap<>();
    private final static Map<Character, Result> getFromCode = new HashMap<>();

    static {
        for (Result result : values()) {
            getFromScore.put(result.getBaseScore(), result);
            getFromCode.put(result.getCode(), result);
        }
    }

    Result(int baseScore, char code) {
        this.baseScore = baseScore;
        this.code = code;
    }

    public int getBaseScore() {
        return baseScore;
    }

    public int getScore() {
        return baseScore * SCORE_FACTOR;
    }

    public char getCode() {
        return code;
    }

    public static Result get(char code) {
        return getFromCode.get(code);
    }

    public static Result get(Shape self, Shape opponent) {
        int baseScore = ((self.getScore() + 4 - opponent.getScore()) % 3);
        return getFromScore.get(baseScore);
    }

    public Shape get(Shape opponent) {
        return Shape.get(((baseScore + opponent.getScore() + 1) % 3) + 1);
    }
}
