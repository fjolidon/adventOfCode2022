package aoc2022.day13;

public class Number implements ListItem {

    private final int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(ListItem item) {
        if (item instanceof Number) {
            return Integer.compare(value, ((Number) item).value);
        }
        return SimpleList.asList(this).compareTo(SimpleList.asList(item));
    }

    public String toString() {
        return Integer.toString(value);
    }
}
