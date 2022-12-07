package aoc2022.common;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * Can be used to run several solutions in parallel.
 */
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

    /**
     * Runs all solutions in parallel asynchronously
     *
     * (This means the method will not block and you will need to use allSolved() to check that all solutions have
     * terminated)
     */
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

    /**
     * Runs all solutions in parallel synchronously
     *
     * (This means the method will block until all solution have terminated)
     */
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
