package aoc2022.day13;

import java.util.Iterator;
import java.util.LinkedList;


public class SimpleList extends LinkedList<ListItem> implements ListItem {

    public SimpleList(ListItem item) {
        add(item);
    }

    public SimpleList(String input) {
        if (input.startsWith("[") && input.endsWith("]")) {
            parseListContent(input.substring(1, input.length() - 1));
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void parseListContent(String input) {
        int depth = 0;
        int startOfItem = 0;
        for (int i=0; i<input.length(); ++i) {
            char c = input.charAt(i);

            if (c == '[') {
                ++depth;
            } else if (c == ']') {
                if (--depth < 0) {
                    throw new IllegalArgumentException("Unmatched ]");
                }
            } else if (depth == 0) {
                if (c == ',') {
                    addItem(input.substring(startOfItem, i));
                    startOfItem = i + 1;
                }
            }
        }
        addItem(input.substring(startOfItem));
    }

    private void addItem(String item) {
        if (!item.isEmpty()) {
            if (item.startsWith("[")) {
                add(new SimpleList(item));
            } else {
                add(new Number(Integer.parseInt(item)));
            }
        }
    }

    public static SimpleList asList(ListItem item) {
        if (item instanceof SimpleList) {
            return (SimpleList)item;
        } else {
            return new SimpleList(item);
        }
    }

    public int compareTo(SimpleList item) {
        Iterator<ListItem> a = iterator();
        Iterator<ListItem> b = item.iterator();

        while(true) {
            if (!a.hasNext()) {
                if (!b.hasNext()) {
                    return 0;
                }
                return -1;
            }
            if (!b.hasNext()) {
                return 1;
            }
            int compare = a.next().compareTo(b.next());
            if (compare != 0) {
                return compare;
            }
        }
    }

    public int compareTo(ListItem item) {
        return compareTo(asList(item));
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(stream().reduce("", (str, item) -> {
            if (str.isEmpty()) {
                return item.toString();
            } else {
                return str + "," + item;
            }
        }, (s1,s2) -> s1 + s2));
        builder.append("]");
        return builder.toString();
    }
}
