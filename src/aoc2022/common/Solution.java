package aoc2022.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;


/**
 * Can handle the solution for a given day
 */
public abstract class Solution {

    private final int day;
    private boolean solved = false;
    private long elapsedNanos = 0;

    public Solution(int day) {
        this.day = day;
    }

    public abstract void runSolution(File input) throws Exception;

    public void runAndWorkbenchSolution(File input) throws Exception {
        long start = System.nanoTime();
        runSolution(input);
        elapsedNanos = System.nanoTime() - start;
    }

    /**
     * @return How long the solution needed to run, in nanoseconds.
     */
    public long getRunningTime() {
        return elapsedNanos;
    }

    public void runSolutionWithDefaultInput() throws Exception {
        runAndWorkbenchSolution(new File(getPathToDefaultInput()));
    }
    public void runInThreadWithDefaultInput(Object monitor) {
        runInThread(new File(getPathToDefaultInput()), monitor);
    }
    public void runInThread(File input, Object monitor) {
        new Thread(() -> {
            try {
                runAndWorkbenchSolution(input);
            } catch(Exception ex) {
                System.err.println("Solution for day " + getDay() + " has generated an exception");
                ex.printStackTrace();
            } finally {
                synchronized (monitor) {
                    monitor.notifyAll();
                }
            }
        }).start();
    }

    protected Stream<String> getInputStream(File input) throws IOException {
        return Files.lines(Path.of(input.getPath()));
    }
    protected void runForEachLine(File input, Consumer<String> consumer) throws IOException {
        getInputStream(input).forEach(consumer);
    }

    protected void setSolved() {
        this.solved = true;
    }

    public boolean isSolved() {
        return solved;
    }

    public int getSolutionAInt() {
        return 0;
    }
    public int getSolutionBInt() {
        return 0;
    }

    public String getSolutionA() {
        return Integer.toString(getSolutionAInt());
    }
    public String getSolutionB() {
        return Integer.toString(getSolutionBInt());
    }

    public int getDay() {
        return day;
    }

    public String getPathToDefaultInput() {
        return String.format("res/input/day%02d.txt", getDay());
    }

    public String getDayAsString() {
        return String.format("Day %02d", getDay());
    }

    public void printSolution() {
        System.out.println("Solutions for " + getDayAsString());
        System.out.println(getSolutionA());
        System.out.println(getSolutionB());
        if (elapsedNanos > 0) {
            System.out.println("Solution took " + elapsedNanos / 1000 + " microseconds to run.");
        }
        System.out.println();
    }
}
