package aoc2022.day10;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day10 extends SolutionFactory {

    public Day10() {
        super(10, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        public final static int CRT_WIDTH = 40;
        public final static int CRT_HEIGHT = 6;

        private final Program program = new Program(CRT_HEIGHT * CRT_WIDTH);
        private final CRT crt = new CRT(CRT_WIDTH, CRT_HEIGHT);

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            runForEachLine(input, program::processInput);
            program.xValues().forEach(crt::processCycle);
            setSolved();
        }

        @Override
        public int getSolutionAInt() {
            return program.getMeaningfullSignalStrengths();
        }

        @Override
        public String getSolutionB() {
            return crt.toString();
        }
    }
}