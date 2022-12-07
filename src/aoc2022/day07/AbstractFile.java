package aoc2022.day07;



public abstract class AbstractFile {

    private final String name;
    private final Directory parent;
    protected final FileProcessor processor;

    public AbstractFile(Directory parent, String name) {
        this.name = name;
        this.parent = parent;
        if (parent != null) {
            parent.addFile(this);
            this.processor = parent.processor;
        } else {
            this.processor = new FileProcessor();
        }
    }
    protected void processFile() {
        this.processor.accept(this);
    }

    public abstract int getSize();

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public FileProcessor getProcessor() {
        return processor;
    }
}
