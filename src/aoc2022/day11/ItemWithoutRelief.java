package aoc2022.day11;

import java.util.Set;
import java.util.TreeMap;


/**
 * This implementation only stores the mod value or the worry level for each base defined by modBases.
 *
 * This is sufficient to check the divisibility of the worry level for any of the modBases, and does not require to
 * store ridiculously huge values of worry level after thousands of rounds.
 *
 * All operations use modulo arithmetics to update the worry level of the item.
 */
public class ItemWithoutRelief implements Item {
    private final Set<Integer> modBases;
    private final TreeMap<Integer, Integer> modValues;

    public ItemWithoutRelief(int startingValue, Set<Integer> modBases) {
        this.modBases = modBases;
        modValues = new TreeMap<>();

        for (int base : modBases) {
            modValues.put(base, startingValue % base);
        }
    }

    public Item add(int value) {
        for (int base : modBases) {
            modValues.put(base, (modValues.get(base) + value) % base);
        }
        return this;
    }
    public Item multiply(int value) {
        for (int base : modBases) {
            modValues.put(base, (modValues.get(base) * value) % base);
        }
        return this;
    }

    @Override
    public Item add(Item item) {
        if (item instanceof ItemWithoutRelief) {
            return add((ItemWithoutRelief)item);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Item multiply(Item item) {
        if (item instanceof ItemWithoutRelief) {
            return multiply((ItemWithoutRelief)item);
        }
        throw new IllegalArgumentException();
    }

    public Item add(ItemWithoutRelief item) {
        for (int base : modBases) {
            modValues.put(base, (modValues.get(base) + item.modValues.get(base)) % base);
        }
        return this;
    }
    public Item multiply(ItemWithoutRelief item) {
        for (int base : modBases) {
            modValues.put(base, (modValues.get(base) * item.modValues.get(base)) % base);
        }
        return this;
    }

    public boolean isDivisibleBy(int value) {
        Integer mod = modValues.get(value);
        if (mod == null) {
            throw new IllegalArgumentException("Can only check divisibility for");
        } else {
            return modValues.get(value) == 0;
        }
    }

    @Override
    public Item applyRelief() {
        return this;
    }
}
