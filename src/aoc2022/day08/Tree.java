package aoc2022.day08;

import java.util.Arrays;


public class Tree {
    private final byte height;
    private volatile boolean visible = false;
    private volatile byte visibilityCount = 0;

    // cache of the highest neighbor in that direction including this tree. This tells us that another tree that has
    // this tree as one of his neighbor in the given direction is only visible if taller than this value
    private final byte[] visibility = new byte[Direction.values().length];

    // direct neighbors in each direction
    private final Tree[] neighbors = new Tree[Direction.values().length];

    private final byte[] scenery = new byte[Direction.values().length];

    public Tree(byte height) {
        this.height = height;
        Arrays.fill(visibility, (byte) -1);
        Arrays.fill(scenery, (byte) -1);
    }

    public void addNeighbor(Tree neighbor, Direction direction) {
        neighbors[direction.ordinal()] = neighbor;
        neighbor.neighbors[direction.getInverse().ordinal()] = this;
    }

    public Tree getNeighbor(Direction direction) {
        return neighbors[direction.ordinal()];
    }

    private void computeVisibility(Direction direction) {
        synchronized (direction) {
            if (visibility[direction.ordinal()] == -1) {
                byte neighborVisibility;
                if (neighbors[direction.ordinal()] == null) {
                    neighborVisibility = -1;
                } else {
                    neighborVisibility = neighbors[direction.ordinal()].getVisibility(direction);
                }
                if (neighborVisibility < height) {
                    visible = true;
                    visibility[direction.ordinal()] = height;
                } else {
                    visibility[direction.ordinal()] = neighborVisibility;
                }
                synchronized (this) {
                    ++visibilityCount;
                }
            }
        }
    }

    public byte getVisibility(Direction direction) {
        computeVisibility(direction);
        return visibility[direction.ordinal()];
    }

    public boolean isVisible() {
        if (visible) {
            return true;
        }
        if (visibilityCount >= visibility.length) {
            return false;
        }
        for (Direction direction : Direction.values()) {
            computeVisibility(direction);
        }
        return visible;
    }

    public int getVisibleForSum() {
        return isVisible() ? 1 : 0;
    }

    private String visibilityColor() {
        return visible ? "\u001b[34m" : "\u001b[31m";
    }

    public String toString() {
        return visibilityColor() + height;
    }

    public int getHeight() {
        return height;
    }

    private byte computeScenery(Direction direction) {
        Tree current = this;
        byte count = 0;
        while (true) {
            current = current.getNeighbor(direction);
            if (current == null) {
                return count;
            }
            ++count;
            if (current.getHeight() >= height) {
                return count;
            }
        }
    }

    public byte getScenery(Direction direction) {
        byte value = scenery[direction.ordinal()];
        if (value == -1) {
            value = scenery[direction.ordinal()] = computeScenery(direction);
        }
        return value;
    }

    public int getSceneryScore() {
        return Arrays.stream(Direction.values()).mapToInt(this::getScenery).reduce(1, (a, b) -> a * b);
    }


}
