package aoc2022.day08;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class Forest {

    public final static int DEFAULT_WIDTH = 99;
    private final int width;
    private Tree topLeft = null;
    private Tree bottomLeft = null;
    private int height = 0;

    public Forest(int width) {
        this.width = width;
    }

    public Forest() {
        this(DEFAULT_WIDTH);
    }

    public void processInputLine(String line) {
        if (line.length() != width) {
            throw new IllegalArgumentException("Line length does not match the width of the forest");
        }
        Tree currentUp = bottomLeft;
        Tree current = null;
        for (char c : line.toCharArray()) {
            Tree newTree = new Tree((byte)(c - '0'));
            if (current == null) {
                bottomLeft = newTree;
            } else {
                current.addNeighbor(newTree, Direction.RIGHT);
            }
            if (currentUp != null) {
                currentUp.addNeighbor(newTree, Direction.DOWN);
                currentUp = currentUp.getNeighbor(Direction.RIGHT);
            }
            current = newTree;
        }
        if (topLeft == null) {
            topLeft = bottomLeft;
        }
        ++height;
    }

    public Stream<Tree> stream(boolean parallel) {
        return StreamSupport.stream(new TreeIterator(), parallel);
    }


    private class TreeIterator implements Spliterator<Tree> {
        private Tree current;
        private Tree nextRow;
        private int rowIndex = 0;
        private int colIndex = 0;
        private int subHeight;

        private TreeIterator() {
            current = topLeft;
            subHeight = height;
            initNextRow();
        }

        private TreeIterator(Tree start, int subHeight) {
            current = start;
            this.subHeight = subHeight;
            initNextRow();
        }

        private void initNextRow() {
            if (current != null) {
                nextRow = current.getNeighbor(Direction.DOWN);
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super Tree> action) {
            if (current == null) {
                return false;
            }

            action.accept(current);

            current = current.getNeighbor(Direction.RIGHT);
            ++colIndex;
            if (current == null) {
                ++rowIndex;
                colIndex = 0;
                if (rowIndex == subHeight) {
                    return true;
                }
                current = nextRow;
                if (current == null) {
                    return true;
                }
                nextRow = current.getNeighbor(Direction.DOWN);
            }
            return true;
        }

        @Override
        public Spliterator<Tree> trySplit() {
            int remaining = subHeight - rowIndex;
            if (remaining <= 1) {
                return null;
            }
            int newSplit = remaining / 2;
            int currentSplit = remaining - newSplit;
            Tree newTree = nextRow;
            for (int i = 1; i < currentSplit; ++i) {
                newTree = newTree.getNeighbor(Direction.DOWN);
            }
            subHeight -= newSplit;
            return new TreeIterator(newTree, newSplit);
        }

        @Override
        public long estimateSize() {
            return ((long) (subHeight - rowIndex) * width) - colIndex;
        }

        @Override
        public int characteristics() {
            return SIZED | SUBSIZED | IMMUTABLE | NONNULL;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        Tree current = topLeft;
        Tree nextRow = topLeft.getNeighbor(Direction.DOWN);
        while (current != null) {
            result.append(current);
            current = current.getNeighbor(Direction.RIGHT);
            if (current == null) {
                current = nextRow;
                result.append("\n");
                if (nextRow != null) {
                    nextRow = nextRow.getNeighbor(Direction.DOWN);
                }
            }
        }
        result.append("\u001b[0m");
        return result.toString();
    }


}
