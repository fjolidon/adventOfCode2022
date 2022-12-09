package aoc2022.day08;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.Stream;


public class Day08 extends SolutionFactory {

    public Day08() {
        super(8, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        private final Forest forest = new Forest();
        private int visibleTrees = 0;
        private int highestScenery = 0;

        public DaySolution(int day) {
            super(day);
        }

        private Stream<String> exampleStream() {
            return Arrays.stream(new String[] {
                    "30373",
                    "25512",
                    "65332",
                    "33549",
                    "35390"
            });
        }

        @Override
        public void runSolution(File input) throws Exception {
            runForEachLine(input, forest::processInputLine);
            //exampleStream().forEach(forest::processInputLine);

            visibleTrees = forest.stream(false).mapToInt(Tree::getVisibleForSum).sum();
            OptionalInt result = forest.stream(true).mapToInt(Tree::getSceneryScore).max();
            if (result.isPresent()) {
                highestScenery = result.getAsInt();
            }
            setSolved();
        }

        @Override
        public int getSolutionAInt() {
            return visibleTrees;
        }

        @Override
        public int getSolutionBInt() {
            return highestScenery;
        }
    }
}