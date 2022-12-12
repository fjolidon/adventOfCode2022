package aoc2022.day09;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day09 extends SolutionFactory {

    public Day09() {
        super(9, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        private final Rope rope = new Rope(10);

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            runForEachLine(input, rope::processInput);
            setSolved();
        }

        @Override
        public int getSolutionAInt() {
            return rope.getVisitedPartA();
        }

        @Override
        public int getSolutionBInt() {
            return rope.getVisitedPartB();
        }
    }
}