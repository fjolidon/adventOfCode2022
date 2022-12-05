package aoc2022.day03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;


public class Group implements Iterable<Rucksack> {

    public final static int STANDARD_SIZE = 3;
    private final List<Rucksack> members;

    public Group(List<Rucksack> members) {
        this.members = members;
        Collections.sort(members);
    }

    @Override
    public Iterator<Rucksack> iterator() {
        return members.iterator();
    }

    public static class Generator<G extends Group, R extends Rucksack> {

        private final Function<List<R>, G> groupConstructor;

        public Generator(Function<List<R>, G> groupConstructor) {
            this.groupConstructor = groupConstructor;
        }

        LinkedList<G> groups = new LinkedList<>();
        ArrayList<R> buffer = new ArrayList<>(STANDARD_SIZE);

        public void add(R rucksack) {
            buffer.add(rucksack);

            if (buffer.size() == STANDARD_SIZE) {
                groups.add(groupConstructor.apply(buffer));
                buffer = new ArrayList<>(STANDARD_SIZE);
            }
        }

        public List<G> generate() {
            if (buffer.isEmpty()) {
                return groups;
            } else {
                throw new IllegalStateException("Amount of rucksacks is not divisible by 3");
            }
        }
    }

    public Rucksack.Item getCommonItem() {
        HashSet<Character> candidates = new HashSet<>();
        HashSet<Character> nextCandidates = new HashSet<>();
        int i=0;

        for (Rucksack member : members) {
            String content = member.getContent();
            if (i == 0) {
                for (int j=0; j<content.length(); ++j) {
                    candidates.add(content.charAt(j));
                }
            } else {
                boolean last = (i == STANDARD_SIZE - 1);

                for (Character c : candidates) {
                    if (content.contains(String.valueOf(c))) {
                        if (last) {
                            return new Rucksack.Item(c);
                        } else {
                            nextCandidates.add(c);
                        }
                    }
                }
                candidates = nextCandidates;
                nextCandidates = new HashSet<>();
            }
            ++i;
        }

        return null;
    }
}
