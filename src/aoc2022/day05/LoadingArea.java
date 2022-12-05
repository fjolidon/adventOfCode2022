package aoc2022.day05;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class LoadingArea<T extends Stack<Crate>> {

    private final HashMap<Integer, Stack<Crate>> stacks = new HashMap<>();
    private final LinkedList<Integer> ids;

    public LoadingArea(Iterator<String> input, Supplier<T> constructor) {
        ids = new LinkedList<>();
        Arrays.stream(input.next().trim().split("\\s\\s\\s")).map(
                Integer::valueOf
        ).forEach(i -> {
            stacks.put(i, constructor.get());
            ids.add(i);
        });

        input.forEachRemaining(processInputLine(
                (i, c) -> stacks.get(ids.get(i)).add(new Crate(c))
        ));
    }

    private LoadingArea(LinkedList<Integer> ids) {
        this.ids = ids;
    }

    public LoadingArea<T> copy() {
        LoadingArea<T> copy = new LoadingArea<>(ids);
        stacks.forEach((key, value) -> copy.stacks.put(key, value.copy()));
        return copy;
    }
    private final static Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

    public void apply(String line, boolean reverse) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            int amount = Integer.parseInt(matcher.group(1));
            Stack<Crate> from = stacks.get(Integer.parseInt(matcher.group(2)));
            Stack<Crate> to = stacks.get(Integer.parseInt(matcher.group(3)));

            from.transferTo(to, amount, reverse);
        } else {
            throw new IllegalArgumentException("Invalid input: " + line);
        }
    }

    public String getTopCrates() {
        return ids.stream()
                .map(stacks::get)
                .map(Stack::peek)
                .map(Crate::getContent)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private static Consumer<String> processInputLine(BiConsumer<Integer, Character> action) {
        return line -> {
            int total = (1 + line.length()) / 4;

            for (int i=0; i<total; ++i) {
                int offset = i*4;
                String current = line.substring(offset, offset+3);
                if (current.startsWith("[") && current.endsWith("]")) {
                    action.accept(i, current.charAt(1));
                }
            }
        };
    }


}
