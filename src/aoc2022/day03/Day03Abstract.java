package aoc2022.day03;

import aoc2022.common.Solution;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;


public abstract class Day03Abstract<G extends Group, R extends Rucksack> extends Solution {

    private int sumPartA;
    private int sumPartB;

    private final Function<List<R>, G> groupConstructor;
    private final Function<String, R> rucksackConstructor;


    public Day03Abstract(int day, Function<List<R>, G> groupConstructor, Function<String, R> rucksackConstructor) {
        super(day);
        this.groupConstructor = groupConstructor;
        this.rucksackConstructor = rucksackConstructor;
    }

    private void runSolutionOnlyFirstPart(File input) throws IOException {
        sumPartA = getInputStream(input).map(
                rucksackConstructor
        ).map(
                R::getCommonItem
        ).mapToInt(
                Rucksack.Item::getPriority
        ).sum();
        sumPartB = 0;
        setSolved(true);
    }

    @Override
    public void runSolution(File input) throws Exception {
        Group.Generator<G, R> groupGenerator = new Group.Generator<>(groupConstructor);
        sumPartA = getInputStream(input).map(
                rucksackConstructor
        ).map(rucksack -> {
                    groupGenerator.add(rucksack);
                    return rucksack.getCommonItem();
                }
        ).mapToInt(
                Rucksack.Item::getPriority
        ).sum();

        sumPartB = groupGenerator.generate().stream().map(
                G::getCommonItem
        ).mapToInt(
                Rucksack.Item::getPriority
        ).sum();
        setSolved(true);
    }

    @Override
    public int getSolutionAInt() {
        return sumPartA;
    }

    @Override
    public int getSolutionBInt() {
        return sumPartB;
    }

}
