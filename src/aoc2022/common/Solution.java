package aoc2022.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;


public abstract class Solution {

    private final int day;
    private boolean solved = false;

    public Solution(int day) {
        this.day = day;
    }

    public abstract void runSolution(File input) throws Exception;
    public void runSolutionWithDefaultInput() throws Exception {
        runSolution(new File(getPathToDefaultInput()));
    }
    public void runInThreadWithDefaultInput(Object monitor) {
        runInThread(new File(getPathToDefaultInput()), monitor);
    }
    public void runInThread(File input, Object monitor) {
        new Thread(() -> {
            try {
                runSolution(input);
            } catch(Exception ex) {
                System.err.println("Solution for day " + getDay() + " has generated an exception");
                ex.printStackTrace();
            } finally {
                synchronized (monitor) {
                    monitor.notify();
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

    protected void setSolved(boolean solved) {
        this.solved = solved;
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
        System.out.println();
    }
}
