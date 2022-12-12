package aoc2022.day09;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Rope {

    private final LinkedList<Node> nodes = new LinkedList<>();
    private Node tailPartA;

    private final Set<Point> visitedPartA = new HashSet<>();
    private final Set<Point> visitedPartB = new HashSet<>();

    public Rope(int length) {
        for (int i=0; i<length; ++i) {
            Node node = new Node();
            if (i == 1) {
                tailPartA = node;
            }
            nodes.add(node);
        }
        visit();
    }

    public void processInput(String input) {
        Direction direction = Direction.getDirection(input.charAt(0));
        int n = Integer.parseInt(input.substring(2));
        for (int i=0; i<n; ++i) {
            apply(direction);
        }
    }

    private void visit() {
        visitedPartA.add(tailPartA.getCurrentPosition());
        visitedPartB.add(nodes.getLast().getCurrentPosition());
    }

    public void apply(Direction direction) {
        Node previous = null;
        for (Node current : nodes) {
            if (previous == null) {
                current.move(direction);
            } else {
                current.moveToward(previous);
            }
            previous = current;
        }
        visit();
    }

    public int getVisitedPartA() {
        return visitedPartA.size();
    }
    public int getVisitedPartB() {
        return visitedPartB.size();
    }
}
