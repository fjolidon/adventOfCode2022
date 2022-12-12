package aoc2022.day09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public enum Direction {
    UP(0, 1, 'U'),
    DOWN(0, -1, 'D'),
    LEFT(-1, 0, 'L'),
    RIGHT(1, 0, 'R');

    private final int x;
    private final int y;
    private final char code;

    private final static Map<Character, Direction> codeMap = Arrays.stream(Direction.values())
            .collect(Collectors.toMap(Direction::getCode, d -> d));

    Direction(int x, int y, char code) {
        this.x = x;
        this.y = y;
        this.code = code;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getCode() {
        return code;
    }

    public static Direction getDirection(char code) {
        return codeMap.get(code);
    }
}
