package aoc2022.common;

/**
 * This can be used as an accumulator for solutions when both part are processing the same values but using a different
 * way to sum them.
 */
public class DualCounter {

    private int partACount = 0;
    private int partBCount = 0;

    public void incrementPartA(int value) {
        partACount += value;
    }

    public void incrementPartB(int value) {
        partBCount += value;
    }

    public int getPartA() {
        return partACount;
    }

    public int getPartB() {
        return partBCount;
    }
}
