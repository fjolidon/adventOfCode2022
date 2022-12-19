package aoc2022.day14;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day14 extends SolutionFactory {

    public Day14() {
        super(14, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        private final Map map = new Map();

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            runForEachLine(input, map::processInput);
            map.finalizeInput();

            //noinspection StatementWithEmptyBody
            while(map.produceSand()) {}

            // System.out.println(map);
            setSolved();
        }

        @Override
        public int getSolutionAInt() {
            return map.getSandCountPartA();
        }

        @Override
        public int getSolutionBInt() {
            return map.getSandCount();
        }
    }
}