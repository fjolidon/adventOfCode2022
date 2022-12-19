package aoc2022.day14;

import java.util.Objects;


public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point(String input) {
        String[] str = input.split(",");
        x = Integer.parseInt(str[0]);
        y = Integer.parseInt(str[1]);
    }

    public Point down() {
        return new Point(x, y + 1);
    }

    public Point downLeft() {
        return new Point(x - 1, y + 1);
    }

    public Point downRight() {
        return new Point(x + 1, y + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
