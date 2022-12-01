package aoc2022.common;

public abstract class Solution {

    private final String[] args;

    public Solution(String[] args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public abstract void runSolutionFirstPart() throws Exception;
    public abstract void runSolution() throws Exception;
}
