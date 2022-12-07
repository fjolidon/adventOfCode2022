package aoc2022.day07;

import java.util.LinkedList;


public class Directory extends AbstractFile {

    private LinkedList<AbstractFile> files = new LinkedList<>();
    private int size = -1;
    private final Directory root;
    private boolean finalized = false;

    public Directory(Directory parent, String name) {
        super(parent, name);
        if (parent == null) {
            root = this;
        } else {
            root = parent.root;
        }

    }

    public void addFile(AbstractFile file) {
        if (finalized) {
            throw new IllegalStateException("Can't add more files once the directory has been finalized");
        }
        files.add(file);
    }

    public int getSize() {
        if (!finalized) {
            throw new IllegalStateException("Need to call finalizeDirectory() before the size of a directory is known");
        }
        return size;
    }

    public void finalizeDirectory() {
        size = files.stream().mapToInt(AbstractFile::getSize).sum();
        finalized = true;
        processFile();
    }

    private Directory getSubDir(String name, boolean update) {
        boolean needsUpdate = update;

        try {
            if (name.equals("..")) {
                return getParent();
            }
            needsUpdate = false;

            if (name.equals("/")) {
                return root;
            }

            for (AbstractFile file : files) {
                if (file.getName().equals(name) && file instanceof Directory) {
                    return (Directory) file;
                }
            }
            throw new IllegalArgumentException("No such subdirectory: " + name);
        } finally {
            if (needsUpdate) {
                finalizeDirectory();
            }
        }

    }

    public Directory getSubDir(String name) {
        return getSubDir(name, false);
    }

    public Directory getSubDirAndUpdate(String name) {
        return getSubDir(name, true);
    }

}
