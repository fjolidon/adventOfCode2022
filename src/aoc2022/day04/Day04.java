package aoc2022.day04;

import aoc2022.common.DualCounter;
import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day04 extends SolutionFactory {

    public Day04() {
        super(4, DaySolution::new);
    }

    private static class DaySolution extends Solution {

        private final DualCounter sum = new DualCounter();

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            getInputStream(input).map(
                    Pair::new
            ).forEach(
                    pair -> {
                        sum.incrementPartA(pair.valueForSumPartA());
                        sum.incrementPartB(pair.valueForSumPartB());
                    }
            );

            setSolved();
        }

        @Override
        public int getSolutionAInt() {
            return sum.getPartA();
        }

        @Override
        public int getSolutionBInt() {
            return sum.getPartB();
        }
    }
}