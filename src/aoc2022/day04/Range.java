package aoc2022.day04;

public class Range {

    private final int from;
    private final int to;

    public Range(String input) {
        String[] array = input.split("-");
        from = Integer.parseInt(array[0]);
        to = Integer.parseInt(array[1]);
    }

    public boolean contains(Range range) {
        return from <= range.from && to >= range.to;
    }

    public boolean overlaps(Range range) {
        return (from >= range.from && from <= range.to) || (to >= range.from && to <= range.to) || (range.from >= from && range.from <= to);
    }
}
