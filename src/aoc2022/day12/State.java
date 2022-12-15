package aoc2022.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;


public abstract class State<T extends State<T>> implements Comparable<State<T>> {


    private final Position position;
    private final T previousState;
    private final Map map;
    private final int distanceFromStart;


    protected State(Position position, T previousState, Map map) {
        this.position = position;
        this.map = map;
        this.previousState = previousState;
        if (previousState == null) {
            this.distanceFromStart = 0;
        } else {
            this.distanceFromStart = previousState.getDistanceFromStart() + 1;
        }
    }

    public Position getPosition() {
        return position;
    }
    public int getDistanceFromStart() {
        return distanceFromStart;
    }
    public T getPrevious() {
        return previousState;
    }

    public int getWeight() {
        return getDistanceFromStart() + getDistanceFromGoal();
    }

    public Map getMap() {
        return map;
    }


    public Collection<T> getNeighbors() {
        ArrayList<T> neighbors = new ArrayList<>(3);
        Arrays.stream(Direction.values()).map(getPosition()::move).forEach(
                neighbor -> tryAndAddNeighbor(neighbor, neighbors)
        );

        return neighbors;
    }

    private void tryAndAddNeighbor(Position neighbor, ArrayList<T> neighbors) {
        if ((getPrevious() == null || !neighbor.equals(getPrevious().getPosition()))
                && neighbor.isValid(getMap()) // valid cell? (not outside map)
                && isNeighborValid(neighbor)) { // valid neighbor? (this is a valid move, regarding elevation)
            neighbors.add(newNeighbor(neighbor));
        }
    }

    protected abstract boolean isNeighborValid(Position position);
    protected abstract T newNeighbor(Position position);


    public abstract int getDistanceFromGoal();
    public abstract boolean isGoal();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked") State<T> state = (State<T>) o;
        return getWeight() == state.getWeight() && Objects.equals(getPosition(), state.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getWeight());
    }

    @Override
    public int compareTo(State<T> o) {
        int distanceCompare = Integer.compare(getWeight(), o.getWeight());
        if (distanceCompare != 0) {
            return distanceCompare;
        } else {
            return Integer.compare(hashCode(), o.hashCode());
        }
    }


}
