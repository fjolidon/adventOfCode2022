package aoc2022.day13;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day13 extends SolutionFactory {

    public Day13() {
        super(13, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        private final DistressSignal signal = new DistressSignal();

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            runForEachLine(input, signal::processInput);
            setSolved();
        }

        @Override
        public int getSolutionAInt() {
            return signal.getPartA();
        }

        @Override
        public int getSolutionBInt() {
            return signal.getPartB();
        }
    }
}