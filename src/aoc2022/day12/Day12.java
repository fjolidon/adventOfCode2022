package aoc2022.day12;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day12 extends SolutionFactory {

    public Day12() {
        super(12, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        private final Map map = new Map();

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            runForEachLine(input, map::processLine);
            map.finalizeInput();
            map.computeShortestPath();

            setSolved();
        }

        @Override
        public int getSolutionAInt() {
            return map.getShortestPathLengthA() - 1;
        }

        @Override
        public int getSolutionBInt() {
            return map.getShortestPathLengthB() - 1;
        }
    }
}