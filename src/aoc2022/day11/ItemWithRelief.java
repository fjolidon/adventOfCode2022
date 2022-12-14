package aoc2022.day11;

import java.util.Set;


/**
 * This is the basic implementation that can simply store the worry level as an integer, as the worry level is reduced
 * after an inspection.
 *
 * All operations use basic arithmetic to adjust the worry level, and the modBases Set is ignored.
 */
public class ItemWithRelief implements Item {

    private final int value;
    private final Set<Integer> modBases;

    public ItemWithRelief(int value, Set<Integer> modBases) {
        this.value = value;
        this.modBases = modBases;
    }

    @Override
    public Item add(int value) {
        return new ItemWithRelief(this.value + value, modBases);
    }

    @Override
    public Item multiply(int value) {
        return new ItemWithRelief(this.value * value, modBases);
    }


    @Override
    public Item add(Item item) {
        if (item instanceof ItemWithRelief) {
            return add((ItemWithRelief)item);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Item multiply(Item item) {
        if (item instanceof ItemWithRelief) {
            return multiply((ItemWithRelief)item);
        }
        throw new IllegalArgumentException();
    }

    public Item add(ItemWithRelief item) {
        return new ItemWithRelief(this.value + item.value, modBases);
    }

    public Item multiply(ItemWithRelief item) {
        return new ItemWithRelief(this.value * item.value, modBases);
    }

    @Override
    public boolean isDivisibleBy(int value) {
        return this.value % value == 0;
    }

    @Override
    public Item applyRelief() {
        return new ItemWithRelief(value / 3, modBases);
    }
}
