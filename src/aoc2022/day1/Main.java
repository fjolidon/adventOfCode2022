package aoc2022.day1;

import aoc2022.common.Solution;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;


public class Main extends Solution {

    private int currentTotal = 0;
    private int highestTotal = 0;
    private final LinkedList<Integer> highestXTotals = new LinkedList<>();

    // How many elves we consider as the top snack carriers for part 2
    private final static int TOP_X_ELVES = 3;

    public static void main(String[] args) {
        new Main(new String[]{ "res/day01/input.txt" }).runSolution();
    }

    public Main(String[] args) {
        super(args);
    }

    @Override
    public void runSolutionFirstPart() throws RuntimeException {
        File input = new File(getArgs()[0]);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    if (currentTotal > highestTotal) {
                        highestTotal = currentTotal;
                    }
                    currentTotal = 0;
                } else {
                    currentTotal += Integer.parseInt(line);
                }
            }

            if (currentTotal > highestTotal) {
                highestTotal = currentTotal;
            }

            System.out.println(highestTotal);

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void runSolution() throws RuntimeException {
        File input = new File(getArgs()[0]);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    checkTotal();
                    currentTotal = 0;
                } else {
                    currentTotal += Integer.parseInt(line);
                }
            }

            checkTotal();

            System.out.println(highestXTotals.getLast());
            System.out.println(highestXTotals.stream().mapToInt(Integer::intValue).sum());

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void checkTotal() {
        if (highestXTotals.size() < TOP_X_ELVES) {
            highestXTotals.add(currentTotal);

            if (highestXTotals.size() == TOP_X_ELVES) {
                Collections.sort(highestXTotals);
            }
        } else if (currentTotal > highestXTotals.getFirst()){
            highestXTotals.removeFirst();
            highestXTotals.add(currentTotal);
            Collections.sort(highestXTotals);
        }
    }
}
