package aoc2022.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class GenerateTemplates {

    private final static String classTemplate = ""
            + "package aoc2022.day%02d;\n"
            + "\n"
            + "import aoc2022.common.Solution;\n"
            + "import aoc2022.common.SolutionFactory;\n"
            + "import java.io.File;\n"
            + "\n"
            + "\n"
            + "public class Day%02d extends SolutionFactory {\n"
            + "\n"
            + "    public Day%02d() {\n"
            + "        super(%d, DaySolution::new);\n"
            + "    }\n"
            + "    \n"
            + "    private static class DaySolution extends Solution {\n"
            + "\n"
            + "        public DaySolution(int day) {\n"
            + "            super(day);\n"
            + "        }\n"
            + "\n"
            + "        @Override\n"
            + "        public void runSolution(File input) throws Exception {\n"
            + "            // TODO implement the solution\n"
            + "        }\n"
            + "    }\n"
            + "}";

    public static void main(String[] args) throws Exception {
        generateDays(4, 25);
    }


    public static void generateDays(int from, int to) throws IOException {
        for (int i = from; i<=to; ++i) {
            generateDay(i);
            generateDayFactory(i);
        }
    }


    public static void generateDay(int day) throws IOException {
        String input = String.format("res/input/day%02d.txt", day);
        String packageFolder = String.format("src/aoc2022/day%02d", day);
        String classFile = String.format("%s/Day%02d.java", packageFolder, day);
        String classContent = String.format(classTemplate, day, day, day, day);

        if (!new File(input).createNewFile() || !new File(packageFolder).mkdir()) {
            throw new IOException("A file or folder for this day already exists");
        }

        FileWriter writer = new FileWriter(classFile);
        writer.write(classContent);
        writer.close();
    }

    public static void generateDayFactory(int day) {
        System.out.printf("                new Day%02d(),%n", day);
    }
}
