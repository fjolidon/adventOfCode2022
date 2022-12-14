package aoc2022.day11;

import aoc2022.common.Solution;
import aoc2022.common.SolutionFactory;
import java.io.File;


public class Day11 extends SolutionFactory {

    public Day11() {
        super(11, DaySolution::new);
    }
    
    private static class DaySolution extends Solution {

        private Monkeys monkeysPartA;
        private Monkeys monkeysPartB;

        public DaySolution(int day) {
            super(day);
        }

        @Override
        public void runSolution(File input) throws Exception {
            monkeysPartA = new Monkeys(ItemWithRelief::new);
            runForEachLine(input, monkeysPartA::processInput);
            monkeysPartA.finalizeInput();

            monkeysPartB = monkeysPartA.copy(ItemWithoutRelief::new);

            monkeysPartA.processRounds(20);
            monkeysPartB.processRounds(10000);

            setSolved();
        }

        @Override
        public int getSolutionAInt() {
            return (int)monkeysPartA.monkeyBusiness();
        }

        @Override
        public String getSolutionB() {
            return Long.toString(monkeysPartB.monkeyBusiness());
        }
    }
}