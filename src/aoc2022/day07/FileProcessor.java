package aoc2022.day07;

import java.util.TreeMap;
import java.util.function.Consumer;


public class FileProcessor implements Consumer<AbstractFile> {

    public final static int MAX_DIR_SIZE = 100000;

    private int deletionCandidatesSum = 0;

    private final TreeMap<Integer, Directory> deletionCandidates = new TreeMap<>();

    @Override
    public void accept(AbstractFile abstractFile) {
        if (abstractFile instanceof Directory) {
            Directory dir = (Directory) abstractFile;
            deletionCandidates.put(dir.getSize(), dir);
            if (dir.getSize() <= MAX_DIR_SIZE) {
                deletionCandidatesSum += dir.getSize();
            }
        }
    }

    public int getDeletionCandidatesSum() {
        return deletionCandidatesSum;
    }

    public int getMinimumCandidate(int neededSpace) {
        return deletionCandidates.ceilingEntry(neededSpace).getValue().getSize();
    }
}
