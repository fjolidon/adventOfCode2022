package aoc2022.day12;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class AStar<T extends State<T>> {

    private final Set<Position> visited = new HashSet<>();
    private final TreeSet<T> notVisited = new TreeSet<>();
    private int closestStateDistance = Integer.MAX_VALUE;
    private T closestState = null;


    public AStar(T firstState) {
        notVisited.add(firstState);
    }

    public boolean visitNext() {
        if (closestState != null && closestState.isGoal()) {
            return true;
        }
        T next;

        do {
            if (notVisited.isEmpty()) {
                throw new IllegalArgumentException("No valid path");
            }
            next = notVisited.pollFirst();
            assert next != null;
        } while(visited.contains(next.getPosition()));

        for (T neighbor : next.getNeighbors()) {
            if (!visited.contains(neighbor.getPosition())) {
                notVisited.add(neighbor);
            }
        }


        int nextDistance = next.getDistanceFromGoal();

        if (next.isGoal()) {
            closestState = next;
            return true;
        }

        if (nextDistance < closestStateDistance) {
            closestStateDistance = nextDistance;
            closestState = next;
        }

        visited.add(next.getPosition());
        return false;
    }

    public int getPathLength() {
        if (closestState == null) {
            return 0;
        }

        return closestState.getDistanceFromStart() + 1;
    }

    public Position[] getPath() {
        int pathLength = getPathLength();
        Position[] list = new Position[pathLength];

        T state = closestState;

        for (int i = pathLength - 1; i >= 0; --i) {
            list[i] = state.getPosition();
            state = state.getPrevious();
        }

        return list;
    }
}
