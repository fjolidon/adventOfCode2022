package aoc2022.day08;

public enum Direction {
    UP,
    DOWN(UP),
    LEFT,
    RIGHT(LEFT);

    private Direction inverse;

    Direction(Direction inverse) {
        this.inverse = inverse;
        inverse.inverse = this;
    }

    Direction() {}

    public Direction getInverse() {
        return inverse;
    }
}
