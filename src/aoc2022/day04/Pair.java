package aoc2022.day04;

public class Pair {
    private final Range a;
    private final Range b;

    public Pair(String input) {
        String[] array = input.split(",");
        a = new Range(array[0]);
        b = new Range(array[1]);
    }

    public boolean hasFullContainment() {
        return a.contains(b) || b.contains(a);
    }

    public boolean hasOverlap() {
        return a.overlaps(b);
    }

    public int valueForSumPartA() {
        return hasFullContainment() ? 1 : 0;
    }

    public int valueForSumPartB() {
        return hasOverlap() ? 1 : 0;
    }
}
