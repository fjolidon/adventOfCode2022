package aoc2022.day07;



public class SimpleFile extends AbstractFile {

    public final int size;

    public SimpleFile(Directory parent, String name, int size) {
        super(parent, name);
        this.size = size;
        processFile();
    }


    @Override
    public int getSize() {
        return size;
    }
}
