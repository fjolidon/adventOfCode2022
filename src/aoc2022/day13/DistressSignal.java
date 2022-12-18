package aoc2022.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class DistressSignal {
    private final LinkedList<Pair> pairs = new LinkedList<>();

    private String previous = null;

    public void processInput(String input) {
        if (!input.isEmpty()) {
            if (previous == null) {
                previous = input;
            } else {
                pairs.add(new Pair(previous, input, pairs.size() + 1));
                previous = null;
            }
        }
    }

    public int getPartA() {
        return pairs.stream().mapToInt(Pair::getCompareValue).sum();
    }

    public int getPartB() {
        ArrayList<SimpleList> allLists = new ArrayList<>(pairs.size()*2 + 2);

        SimpleList startOfSignal = new SimpleList("[[2]]");
        SimpleList endOfSignal = new SimpleList("[[6]]");
        allLists.add(startOfSignal);
        allLists.add(endOfSignal);

        pairs.forEach(p -> p.addPairToList(allLists));

        Collections.sort(allLists);

        int startIndex = 0;
        for (int i = 1; i <= allLists.size(); ++i) {
            SimpleList current = allLists.get(i-1);
            if (current == startOfSignal) {
                startIndex = i;
            } else if (current == endOfSignal) {
                return startIndex * i;
            }
        }
        return 0;
    }

    public String toString() {
        return pairs.stream().reduce("", (str, list) -> {
            if (str.isEmpty()) {
                return list.toString();
            } else {
                return str + "\n\n" + list;
            }
        }, String::concat);
    }

}
