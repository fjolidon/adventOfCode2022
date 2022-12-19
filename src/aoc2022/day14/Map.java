package aoc2022.day14;

import aoc2022.day14.inputparsing.Structure;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;


public class Map {

    private int lowest = 0;
    private int leftMost = 500;
    private int rightMost = 500;

    private int sandCount = 0;
    private int sandCountPartA = -1;

    private Cell[][] cells;

    private final LinkedList<Structure> structures = new LinkedList<>();

    public final static Point SAND_SOURCE = new Point(500, 0);

    public void processInput(String input) {
        Structure structure = new Structure(input);

        if (structure.getLowest() > lowest) {
            lowest = structure.getLowest();
        }
        if (structure.getLeftMost() < leftMost) {
            leftMost = structure.getLeftMost();
        }
        if (structure.getRightMost() > rightMost) {
            rightMost = structure.getRightMost();
        }

        structures.add(structure);
    }

    public void finalizeInput() {
        lowest += 2;
        leftMost = 500 - lowest;
        rightMost = 500 + lowest;

        cells = new Cell[1 + lowest][1 + rightMost - leftMost];
        int i = 0;
        for (; i < cells.length - 1; ++i) {
            for (int j=0; j<cells[0].length; ++j) {
                cells[i][j] = new Cell();
            }
        }

        // last row is the floor
        for (int j=0; j<cells[i].length; ++j) {
            Cell cell = new Cell();
            cell.setContent(Cell.Content.ROCK);
            cells[i][j] = cell;
        }

        getCell(SAND_SOURCE).setContent(Cell.Content.SAND_SOURCE);

        structures.forEach(s -> s.iteratePoints().forEachRemaining(
                p -> getCell(p).setContent(Cell.Content.ROCK)
        ));

    }

    private int getArrayX(int worldX) {
        return worldX - leftMost;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Cell[] cell : cells) {
            builder.append(Arrays.stream(cell).map(Cell::toString).collect(Collectors.joining()));
            builder.append('\n');
        }

        return builder.toString();
    }

    public boolean produceSand() {
        FallingSand sand = new FallingSand(this);
        //noinspection StatementWithEmptyBody
        while (sand.tryFall()) {}

        getCell(sand.getPosition()).setContent(Cell.Content.SAND);

        if (sand.getPosition().y + 1 == lowest && sandCountPartA == -1) {
            sandCountPartA = sandCount;
        }
        ++sandCount;
        return !isBlockingSand(sand.getPosition());
    }

    private Cell getCell(Point position) {
        return cells[position.y][getArrayX(position.x)];
    }

    public boolean isFree(Point position) {
        return getCell(position).getContent() == Cell.Content.AIR;
    }

    public boolean isOutOfBounds(Point position) {
        return (position.y < 0 || position.y > lowest || position.x < leftMost || position.y > rightMost);
    }

    public boolean isBlockingSand(Point position) {
        return position.equals(SAND_SOURCE);
    }

    public int getSandCountPartA() {
        return sandCountPartA;
    }
    public int getSandCount() {
        return sandCount;
    }
}
