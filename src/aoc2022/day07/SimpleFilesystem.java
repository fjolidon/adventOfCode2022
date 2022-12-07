package aoc2022.day07;

public class SimpleFilesystem {

    private final Directory root = new Directory(null, "");
    private Directory currentDir = root;
    private Command currentCommand = null;
    public final static int TOTAL_DISK_SPACE = 70000000;
    public final static int UPDATE_SIZE = 30000000;

    /**
     * Process one line of input
     * @param line one line of input
     */
    public void processLine(String line) {
        if (line.startsWith("$")) {
            processCommand(line);
        } else {
            processCommandOutput(line);
        }
    }

    private void processCommand(String line) {
        if (currentCommand != null) {
            currentDir = currentCommand.apply();
        }
        currentCommand = new Command(currentDir, line);
    }

    private void processCommandOutput(String line) {
        currentCommand.addOuputLine(line);
    }

    /**
     * To be called once after the last line of input has been processed by processLine()
     */
    public void finalizeFilesystem() {
        currentDir = currentCommand.apply();
        while (currentDir != root) {
            currentDir.finalizeDirectory();
            currentDir = currentDir.getParent();
        }
        root.finalizeDirectory();
    }

    /**
     * @return the FileProcessor instance for this FileSystem
     */
    public FileProcessor getProcessor() {
        return root.getProcessor();
    }

    /**
     * Returns the currently available space on the system. Should only be called after calling finalizeFilesystem().
     *
     * @return how much is currently available on the system
     */
    public int getAvailableSpace() {
        return TOTAL_DISK_SPACE - root.getSize();
    }

    /**
     * Returns how much space is missing before the update can be applied. Should only be called after calling
     * finalizeFilesystem().
     *
     * @return how much is missing before the update can be applied
     */
    public int getMissingSpaceForUpdate() {
        return UPDATE_SIZE - getAvailableSpace();
    }
}
