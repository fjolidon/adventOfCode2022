package aoc2022.day01;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;


public class Day01 extends SolutionFactory {

    // How many elves we consider as the top snack carriers for part 2
    private final static int TOP_X_ELVES = 3;

    public Day01() {
        super(1, DaySolution::new);
    }

    private static class DaySolution extends Solution {
        private int currentTotal = 0;
        private int highestTotal = 0;
        private final LinkedList<Integer> highestXTotals = new LinkedList<>();
        public DaySolution(int day) {
            super(day);
        }

        public void runSolutionFirstPart(File input) throws RuntimeException {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(input));

                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty()) {
                        if (currentTotal > highestTotal) {
                            highestTotal = currentTotal;
                        }
                        currentTotal = 0;
                    } else {
                        currentTotal += Integer.parseInt(line);
                    }
                }

                if (currentTotal > highestTotal) {
                    highestTotal = currentTotal;
                }

                highestXTotals.add(highestTotal);
                setSolved();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void runSolution(File input) throws RuntimeException {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(input));

                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty()) {
                        checkTotal();
                        currentTotal = 0;
                    } else {
                        currentTotal += Integer.parseInt(line);
                    }
                }

                checkTotal();
                setSolved();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public String getSolutionA() {
            return highestXTotals.getLast().toString();
        }

        @Override
        public String getSolutionB() {
            return Integer.toString(highestXTotals.stream().mapToInt(Integer::intValue).sum());
        }

        private void checkTotal() {
            if (highestXTotals.size() < TOP_X_ELVES) {
                highestXTotals.add(currentTotal);

                if (highestXTotals.size() == TOP_X_ELVES) {
                    Collections.sort(highestXTotals);
                }
            } else if (currentTotal > highestXTotals.getFirst()){
                highestXTotals.removeFirst();
                highestXTotals.add(currentTotal);
                Collections.sort(highestXTotals);
            }
        }
    }

}
