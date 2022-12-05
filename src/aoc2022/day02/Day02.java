package aoc2022.day02;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;


public class Day02 extends SolutionFactory {


    public Day02() {
        super(2, DaySolution::new);
    }

    private static class DaySolution extends Solution {

        private final Score scoreA = new Score();
        private final Score scoreB = new Score();
        private final Consumer<String> addResultA = scoreA::addResultPartA;
        private final Consumer<String> addResultB = addResultA.andThen(scoreB::addResultPartB);

        public DaySolution(int day) {
            super(day);
        }


        private void runSolutionImpl(File input, Consumer<String> addResult) {
            try {
                runForEachLine(input, addResult);
            } catch(IOException ex) {
                ex.printStackTrace();
            }
            setSolved(true);
        }

        public void runSolutionFirstPart(File input) {
            runSolutionImpl(input, addResultA);
        }

        @Override
        public void runSolution(File input) {
            runSolutionImpl(input, addResultB);
        }


        @Override
        public String getSolutionA() {
            return Integer.toString(scoreA.getTotal());
        }

        @Override
        public String getSolutionB() {
            return Integer.toString(scoreB.getTotal());
        }

    }


}
