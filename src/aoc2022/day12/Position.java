package aoc2022.day12;

import java.util.Objects;


public class Position {

    private final int currentX;
    private final int currentY;

    public Position(int currentX, int currentY) {
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getDistance(Position position) {
        return Math.abs(currentX - position.currentX) + Math.abs(currentY - position.currentY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position state = (Position) o;
        return currentX == state.currentX && currentY == state.currentY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentX, currentY);
    }

    public boolean isValid(Map map) {
        return currentX >= 0 && currentX < map.getWidth() && currentY >= 0 && currentY < map.getLength();
    }

    public Position move(Direction direction) {
        return new Position(currentX + direction.getX(), currentY + direction.getY());
    }
}
