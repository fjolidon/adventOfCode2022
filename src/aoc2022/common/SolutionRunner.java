package aoc2022.common;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class SolutionRunner {

    private final SortedMap<Integer, Solution> solutions = new TreeMap<>();

    public SolutionRunner(Solution[] solutions) {
        this(List.of(solutions));
    }

    public SolutionRunner(Iterable<Solution> solutions) {
        addSolutions(solutions);
    }

    private void addSolutions(Iterable<Solution> solutions) {
        for (Solution solution : solutions) {
            addSolution(solution);
        }
    }

    private void addSolution(Solution solution) {
        solutions.put(solution.getDay(), solution);
    }

    public boolean allSolved() {
        for (Solution solution : solutions.values()) {
            if (!solution.isSolved()) {
                return false;
            }
        }
        return true;
    }

    public void runAllSolutionsASync() {
        for (Solution solution : solutions.values()) {
            solution.runInThreadWithDefaultInput(this);
        }
    }

    public void runAndPrintSolutionForDay(int day) throws Exception {
        runSolutionForDay(day);
        printSolutionForDay(day);
    }

    public void runSolutionForDay(int day) throws Exception{
        solutions.get(day).runSolutionWithDefaultInput();
    }

    public synchronized void runAllSolutions() {
        runAllSolutionsASync();
        do {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        } while(!allSolved());
    }

    public void printSolutionForDay(int day) {
        solutions.get(day).printSolution();
    }

    public void printAllSolutions() {
        for (Solution solution : solutions.values()) {
            solution.printSolution();
        }
    }
}
