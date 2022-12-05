package aoc2022.day03;

public class Day03Polynomial extends Day03Abstract<Group, Rucksack> {

    public Day03Polynomial(int day) {
        super(day, Group::new, Rucksack::new);
    }
}
