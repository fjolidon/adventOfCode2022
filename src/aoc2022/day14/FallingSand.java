package aoc2022.day14;

import java.util.List;
import java.util.function.UnaryOperator;


public class FallingSand {

    private Point position;
    private final Map map;
    private boolean outOfBounds = false;

    private final static List<UnaryOperator<Point>> MOVEMENTS = List.of(
            Point::down,
            Point::downLeft,
            Point::downRight
    );

    public FallingSand(Map map) {
        this.map = map;
        position = Map.SAND_SOURCE;
    }

    public boolean tryFall() {
        if (outOfBounds) {
            return false;
        }

        for (UnaryOperator<Point> move : MOVEMENTS) {
            Point newPosition = move.apply(position);

            if (map.isOutOfBounds(newPosition)) {
                outOfBounds = true;
                position = newPosition;
                return false;
            }
            if (map.isFree(newPosition)) {
                position = newPosition;
                return true;
            }
        }

        return false;
    }

    public boolean isOutOfBounds() {
        return outOfBounds;
    }

    public Point getPosition() {
        return position;
    }
}
