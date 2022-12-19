package aoc2022.day14.inputparsing;

import aoc2022.day14.Point;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class Structure extends LinkedList<Line> {

    private int lowest = 0;
    private int leftMost = 500;
    private int rightMost = 500;

    public Structure(String input) {
        Iterator<Point> points = Arrays.stream(input.split(" -> "))
                .map(Point::new)
                .collect(Collectors.toList())
                .iterator();

        Point previous = points.next();

        while(points.hasNext()) {
            Point current = points.next();
            add(Line.getLine(previous, current));
            previous = current;
        }

    }

    public boolean add(Line line) {
        if (line.getLowest() > lowest) {
            lowest = line.getLowest();
        }
        if (line.getLeftMost() < leftMost) {
            leftMost = line.getLeftMost();
        }
        if (line.getRightMost() > rightMost) {
            rightMost = line.getRightMost();
        }
        return super.add(line);
    }

    public int getLowest() {
        return lowest;
    }

    public int getLeftMost() {
        return leftMost;
    }

    public int getRightMost() {
        return rightMost;
    }

    public Iterator<Point> iteratePoints() {
        return new Iterator<>() {

            private final Iterator<Line> lineIterator = iterator();
            private Iterator<Point> current = lineIterator.next().iterator(false);

            @Override
            public boolean hasNext() {
                if (current.hasNext()) {
                    return true;
                }
                if (!lineIterator.hasNext()) {
                    return false;
                }
                current = lineIterator.next().iterator(true);
                return hasNext();
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    return current.next();
                }
                throw new NoSuchElementException();
            }
        };
    }
}
