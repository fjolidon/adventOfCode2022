package aoc2022.day02;

public class Score {
    private int total = 0;

    public void addResultPartA(String line) {
        total += Shape.getScoreForLinePartA(line);
    }

    public void addResultPartB(String line) {
        total += Shape.getScoreForLinePartB(line);
    }

    public int getTotal() {
        return total;
    }
}
