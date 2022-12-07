package aoc2022.day16;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day16 extends SolutionFactory {

    public Day16() {
        super(16, DaySolution::new);
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