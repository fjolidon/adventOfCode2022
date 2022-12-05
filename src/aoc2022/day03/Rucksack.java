package aoc2022.day03;

public class Rucksack implements Comparable<Rucksack> {

    private final String first;
    private final String second;
    private final String full;

    public Rucksack(String input) {
        full = input;
        int half = input.length() / 2;
        first = input.substring(0, half);
        second = input.substring(half);
    }

    public Item getCommonItem() {
        for (int i=0; i<first.length(); ++i) {
            char c = first.charAt(i);
            if (second.contains(Character.toString(c))) {
                return new Item(c);
            }
        }
        return null;
    }

    @Override
    public int compareTo(Rucksack o) {
        return Integer.compare(full.length(), o.full.length());
    }

    public static class Item {

        private final char code;
        private int priority = -1;

        public Item(char code) {
            this.code = code;
        }

        public Item(int priority) {
            this.code = 0;
            this.priority = priority;
        }

        public int getPriority() {
            if (priority == -1) {
                if (code >= 'a' && code <= 'z') {
                    priority = 1 + code - 'a';
                } else if (code >= 'A' && code <= 'Z') {
                    priority = 27 + code - 'A';
                } else {
                    priority = 0;
                }
            }
            return priority;
        }
    }

    public String getFirstCompartment() {
        return first;
    }

    public String getSecondCompartment() {
        return second;
    }

    public String getContent() {
        return full;
    }
}
