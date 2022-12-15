package aoc2022.day12;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;


public class Map {
    private byte[][] elevations;

    private final LinkedList<String> input = new LinkedList<>();


    private Position start;
    private Position goal;
    private Position[] shortestPathA;
    private Position[] shortestPathB;


    public void processLine(String line) {
        input.add(line);
    }

    public void finalizeInput() {
        elevations = new byte[input.size()][];
        int width = 0;

        int i = 0;
        for (String line : input) {
            if (i == 0) {
                width = line.length();
            } else if (line.length() != width) {
                throw new IllegalArgumentException("The map should be a rectangle");
            }
            if (line.contains("S")) {
                start = new Position(line.indexOf('S'), i);
                line = line.replace("S", "a");
            }

            if (line.contains("E")) {
                goal = new Position(line.indexOf('E'), i);
                line = line.replace("E", "z");
            }

            elevations[i++] = line.getBytes(StandardCharsets.UTF_8);
        }

    }

    public byte getElevation(Position position) {
        return elevations[position.getCurrentY()][position.getCurrentX()];
    }

    public Position getGoal() {
        return goal;
    }

    public Position getStart() {
        return start;
    }

    public int getWidth() {
        return elevations[0].length;
    }

    public int getLength() {
        return elevations.length;
    }


    public void computeShortestPath() {

        AStar<StatePartA> aStarA = new AStar<>(new StatePartA(getStart(), this));

        while (!aStarA.visitNext()) {
            shortestPathA = aStarA.getPath();
        }
        shortestPathA = aStarA.getPath();

        AStar<StatePartB> aStarB = new AStar<>(new StatePartB(getGoal(), this));

        while (!aStarB.visitNext()) {
            shortestPathB = aStarB.getPath();
        }
        shortestPathB = aStarB.getPath();

    }

    public int getShortestPathLengthA() {
        if (shortestPathA == null) {
            throw new IllegalStateException("computeShortestPath() must be called first");
        }
        return shortestPathA.length;
    }

    public int getShortestPathLengthB() {
        if (shortestPathB == null) {
            throw new IllegalStateException("computeShortestPath() must be called first");
        }
        return shortestPathB.length;
    }

    public String toString() {
        String[] lines = new String[elevations.length];
        int i=0;
        for (byte[] elevation : elevations) {
            lines[i++] = new String(elevation);
        }
        if (shortestPathA != null) {
            for (Position position : shortestPathA) {
                updateChar(lines, position, '\0');
            }
        }
        updateChar(lines, start, '+');
        updateChar(lines, goal, '=');

        return String.join("\n", lines);
    }

    private static void updateChar(String[] array, Position position, char newChar) {
        String target = array[position.getCurrentY()];
        StringBuilder result = new StringBuilder(target);
        if (newChar == '\0') {
            newChar = Character.toUpperCase(target.charAt(position.getCurrentX()));
        }
        result.setCharAt(position.getCurrentX(), newChar);
        array[position.getCurrentY()] = result.toString();
    }

}
