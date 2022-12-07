package aoc2022.day07;

import java.util.LinkedList;


public class Command {

    private final LinkedList<String> output = new LinkedList<>();
    private final String name;
    private final Directory workingDir;

    public Command(Directory workingDir, String name) {
        this.name = name.substring(2);
        this.workingDir = workingDir;
    }

    public void addOuputLine(String line) {
        output.add(line);
    }

    public Directory apply() {
        if (name.startsWith("cd")) {
            return workingDir.getSubDirAndUpdate(name.substring(3));
        } else if (name.startsWith("ls")) {
            for (String line : output) {
                String[] param = line.split(" ");
                if (param[0].equals("dir")) {
                    new Directory(workingDir, param[1]);
                } else {
                    new SimpleFile(workingDir, param[1], Integer.parseInt(param[0]));
                }
            }
            return workingDir;
        } else {
            throw new IllegalArgumentException("Unknown command: " + name);
        }
    }
}
