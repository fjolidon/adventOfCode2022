package aoc2022.day25;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day25 extends SolutionFactory {

    public Day25() {
        super(25, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            // TODO implement the solution
            setSolved();
        }
    }
}