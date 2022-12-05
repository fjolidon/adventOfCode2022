package aoc2022.day05;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;


public class Day05 extends SolutionFactory {


    public Day05() {
        super(5, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        private LoadingArea<? extends Stack<Crate>> loadingAreaA = null;
        private LoadingArea<? extends Stack<Crate>> loadingAreaB = null;

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            LinkedList<String> initialState = new LinkedList<>();

            Iterator<String> iterator = getInputStream(input).iterator();
            String initLine;
            while (!(initLine = iterator.next()).isEmpty()) {
                initialState.add(initLine);
            }
            loadingAreaA = new LoadingArea<>(initialState.descendingIterator(), FastTransferStack::new);
            loadingAreaB = loadingAreaA.copy();

            iterator.forEachRemaining(line -> {
                loadingAreaA.apply(line, true);
                loadingAreaB.apply(line, false);
            });

            setSolved();
        }

        @Override
        public String getSolutionA() {
            if (loadingAreaA == null) {
                return super.getSolutionA();
            }
            return loadingAreaA.getTopCrates();
        }

        @Override
        public String getSolutionB() {
            if (loadingAreaB == null) {
                return super.getSolutionB();
            }
            return loadingAreaB.getTopCrates();
        }
    }
}