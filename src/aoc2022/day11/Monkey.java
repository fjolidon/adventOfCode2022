package aoc2022.day11;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;


/**
 * This class is used to represent one monkey.
 *
 * This stores the items currently held by the monkey, as well as lambda expressions that model the monkey's behavior.
 */
public class Monkey implements Comparable<Monkey> {

    // id of the monkey
    private final int id;

    // this represents the operation on the worry level of an item when being inspected by the monkey
    private Function<Item, Item> operation;

    // the items currently held by the monkey
    private final LinkedList<Item> items = new LinkedList<>();

    // this is used to decide where to throw an item after it has been inspected
    private Function<Item, Monkey> throwTarget;

    // constructor used to generate new items
    private final BiFunction<Integer, Set<Integer>, Item> itemConstructor;

    // how many items have been inspected by this monkey
    private long inspectCount = 0;

    public Monkey(int id, BiFunction<Integer, Set<Integer>, Item> itemConstructor) {
        this.id = id;
        this.itemConstructor = itemConstructor;
    }

    /**
     * Initialize all parameters for this Monkey.
     * @param startingItems An array containing the worry level for each item held by this monkey
     * @param operation A lambda function to model the increase of the worry level during an item inspection
     * @param test A predicate to decide where to throw an item
     * @param throwIfTrue The monkey to throw the item to when test is true
     * @param throwIfFalse The monkey to throw the item to when test is false
     * @param modBases A set of possible divisors used by the test predicate
     */
    public void init(int[] startingItems,
                     UnaryOperator<Item> operation,
                     Predicate<Item> test,
                     Monkey throwIfTrue,
                     Monkey throwIfFalse,
                     Set<Integer> modBases) {
        inspectCount = 0;
        items.clear();
        for (int item : startingItems) {
            items.add(itemConstructor.apply(item, modBases));
        }
        this.operation = operation.andThen(this::inspectItem);
        this.throwTarget = item -> test.test(item) ? throwIfTrue : throwIfFalse;
    }

    private Item inspectItem(Item item) {
        ++inspectCount;
        return item.applyRelief();
    }

    /**
     * For each item currently held by this monkey:
     * - inspect it and adjust the worry level
     * - throw it as the appropriate monkey
     */
    public void processRound() {
        //
        items.stream().map(operation).forEach(item -> throwTarget.apply(item).throwItemAt(item));
        items.clear();
    }

    /**
     * Receives one item from another monkey
     * @param item An item thrown by another monkey
     */
    public void throwItemAt(Item item) {
        items.addLast(item);
    }

    /**
     * @return The ID for this monkey
     */
    public int getID() {
        return id;
    }

    /**
     * @return The total amount of items that have been inspected by this Monkey.
     */
    public long getInspectCount() {
        return inspectCount;
    }

    @Override
    public int compareTo(Monkey monkey) {
        return Long.compare(inspectCount, monkey.inspectCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Monkey monkey = (Monkey) o;
        return id == monkey.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Monkey " + id + ": " + items.stream().map(Object::toString).reduce("", (result,str) -> {
            if (result.isEmpty()) {
                return str;
            } else {
                return result + ", " + str;
            }
        });
    }
}
