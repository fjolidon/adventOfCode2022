package aoc2022.day10;

import java.util.Arrays;
import java.util.stream.IntStream;


public class Program {
    private final int[] xValues;

    private int currentCycle = 1;
    private int currentSum = 1;
    private int meaningfullSignalStrengths = 0;

    public Program(int nbCycles) {
        xValues = new int[nbCycles];
    }

    public void processInput(String input) {
        if (input.equals("noop")) {
            nextValue(0);
        } else {
            nextValue(0);
            nextValue(Integer.parseInt(input.substring(5)));
        }
    }

    private void nextValue(int value) {
        int arrayIndex = currentCycle++ - 1;

        if (arrayIndex < xValues.length) {
            xValues[arrayIndex] = currentSum;
            currentSum += value;

            if (currentCycle % 40 == 20) {
                meaningfullSignalStrengths += (currentSum * currentCycle);
            }
        }
    }

    public int getMeaningfullSignalStrengths() {
        return meaningfullSignalStrengths;
    }

    public IntStream xValues() {
        return Arrays.stream(xValues);
    }
}
