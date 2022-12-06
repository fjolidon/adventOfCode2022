package aoc2022.day06;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Day06 extends SolutionFactory {

    public Day06() {
        super(6, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        public DaySolution(int day) {
            super(day);
        }

        private int startOfStream = 0;
        private int startOfMessage = 0;

        @Override
        public void runSolution(File input) throws Exception {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String data = reader.readLine();
            reader.close();
            int i=0;
            while (!checkStartOfStream(data, i)) {
                ++i;
            }

            startOfStream = i + 3;
            StartOfMessage checkStartOfMessage = new StartOfMessage();
            while (!checkStartOfMessage.nextChar(data.charAt(i))) {
                ++i;
            }
            startOfMessage = i;
            setSolved();
        }

        private static boolean checkStartOfStream(String input, int offset) {
            for (int i = 0; i < 3; ++i) {
                for (int j = i + 1; j < 4; ++j) {
                    if (input.charAt(offset + i) == input.charAt(offset + j)) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public int getSolutionAInt() {
            return startOfStream + 1;
        }

        @Override
        public int getSolutionBInt() {
            return startOfMessage + 1;
        }
    }
}