package aoc2022.day13;

import java.util.List;


public class Pair {

    private final SimpleList left;
    private final SimpleList right;
    private final int index;

    private final int compareValue;

    public Pair(String left, String right, int index) {
        this.left = new SimpleList(left);
        this.right = new SimpleList(right);
        this.index = index;

        compareValue = this.left.compareTo(this.right) < 0 ? index : 0;
    }

    public int getCompareValue() {
        return compareValue;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("== Pair %d ==%n", index));
        builder.append("- Compare " + left + " vs " + right + "\n");
        builder.append(compareValue);
        return builder.toString();
    }

    public void addPairToList(List<SimpleList> list) {
        list.add(left);
        list.add(right);
    }
}
