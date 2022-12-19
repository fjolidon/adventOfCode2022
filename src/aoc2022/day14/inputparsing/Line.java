package aoc2022.day14.inputparsing;

import aoc2022.day14.Point;
import java.util.Iterator;
import java.util.NoSuchElementException;


public abstract class Line {


    private static class HorizontalLine extends Line {
        private final Point left;
        private final Point right;
        private final boolean inverse;
        private final int length;

        public HorizontalLine(Point a, Point b) {
            if (a.y != b.y) {
                throw new IllegalArgumentException();
            }
            if (a.x < b.x) {
                left = a;
                right = b;
                inverse = false;
            } else {
                left = b;
                right = a;
                inverse = true;
            }
            length = Math.abs(a.x - b.x);
        }

        @Override
        public int getLowest() {
            return left.y;
        }

        @Override
        public int getLeftMost() {
            return left.x;
        }

        @Override
        public int getRightMost() {
            return right.x;
        }

        @Override
        protected int getLength() {
            return length;
        }


        @Override
        protected Point getPoint(int position) {
            if (inverse) {
                position = length - position;
            }
            return new Point(left.x + position, left.y);
        }
    }

    private static class VerticalLine extends Line {
        private final Point top;
        private final Point bottom;
        private final boolean inverse;
        private final int length;

        public VerticalLine(Point a, Point b) {
            if (a.x != b.x) {
                throw new IllegalArgumentException();
            }
            if (a.y < b.y) {
                top = a;
                bottom = b;
                inverse = false;
            } else {
                bottom = a;
                top = b;
                inverse = true;
            }
            length = Math.abs(a.y - b.y);
        }

        @Override
        public int getLowest() {
            return bottom.y;
        }

        @Override
        public int getLeftMost() {
            return bottom.x;
        }

        @Override
        public int getRightMost() {
            return bottom.x;
        }

        @Override
        protected int getLength() {
            return length;
        }

        @Override
        protected Point getPoint(int position) {
            if (inverse) {
                position = length - position;
            }
            return new Point(top.x, top.y + position);
        }
    }


    public static Line getLine(Point a, Point b) {
        if (a.x == b.x) {
            return new VerticalLine(a, b);
        } else if (a.y == b.y) {
            return new HorizontalLine(a, b);
        }
        throw new IllegalArgumentException("Only horizontal and vertical lines are supported");
    }

    public abstract int getLowest();
    public abstract int getLeftMost();
    public abstract int getRightMost();

    protected abstract int getLength();
    protected abstract Point getPoint(int position);

    private class PointIterator implements Iterator<Point> {

        private int current = 0;

        PointIterator(boolean skipFirst) {
            if (skipFirst) {
                ++current;
            }
        }

        @Override
        public boolean hasNext() {
            return current <= getLength();
        }

        @Override
        public Point next() {
            if (hasNext()) {
                return getPoint(current++);
            }
            throw new NoSuchElementException();
        }
    }

    public Iterator<Point> iterator(boolean skipFirst) {
        return new PointIterator(skipFirst);
    }

}
