package aoc2022.day07;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day07 extends SolutionFactory {

    public Day07() {
        super(7, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        private final SimpleFilesystem filesystem = new SimpleFilesystem();

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            runForEachLine(input, filesystem::processLine);
            filesystem.finalizeFilesystem();
            setSolved();
        }

        @Override
        public int getSolutionAInt() {
            return filesystem.getProcessor().getDeletionCandidatesSum();
        }

        @Override
        public int getSolutionBInt() {
            return filesystem.getProcessor().getMinimumCandidate(filesystem.getMissingSpaceForUpdate());
        }
    }
}