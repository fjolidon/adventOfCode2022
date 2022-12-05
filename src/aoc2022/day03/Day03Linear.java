package aoc2022.day03;

public class Day03Linear extends Day03Abstract<GroupLinear, RuckSackLinear> {

    public Day03Linear(int day) {
        super(day, GroupLinear::new, RuckSackLinear::new);
    }
}
