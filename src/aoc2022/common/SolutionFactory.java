package aoc2022.common;

import java.util.function.Function;


/**
 * This can be used to generate an instance of Solution for a given day
 */
public abstract class SolutionFactory {

    private final int day;
    private final Function<Integer, Solution> constructor;

    protected SolutionFactory(int day, Function<Integer, Solution> constructor) {
        this.day = day;
        this.constructor = constructor;
    }

    public Solution get() {
        return constructor.apply(day);
    }
    public int getDay() {
        return day;
    }
}
