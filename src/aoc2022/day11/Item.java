package aoc2022.day11;

/**
 * Interface that represents an item with the corresponding worry level
 */
public interface Item {

    /**
     * Wrapper class that can hold either an Item instance or an int.
     *
     * This is used to have generic operand to build operations, as the second operand of an operation can either be
     * a number (new = old + 4) or a reference to an item (new = old + old)
     */
    class Value {

        private final boolean isInt;
        private final int intValue;
        private final Item itemValue;

        public Value(int value) {
            this.intValue = value;
            this.itemValue = null;
            isInt = true;
        }

        public Value(Item value) {
            this.intValue = 0;
            this.itemValue = value;
            isInt = false;
        }

    }

    /**
     * Add the given value to the Item's worry level
     * @param value an integer value
     * @return a new Item with an increased worry level
     */
    Item add(int value);

    /**
     * Multiply the given value with the Item's worry level
     * @param value an integer value
     * @return a new Item with an increased worry level
     */
    Item multiply(int value);

    /**
     * Add the given Item's worry level to this Item's worry level
     * @param item an Item
     * @return a new Item with an increased worry level
     */
    Item add(Item item);

    /**
     * Multiply the given Item's worry level with this Item's worry level
     * @param item an Item
     * @return a new Item with an increased worry level
     */
    Item multiply(Item item);

    /**
     * Add the given value to the Item's worry level
     * @param value a worry level value (either Item or integer)
     * @return a new Item with an increased worry level
     */
    default Item add(Value value) {
        return value.isInt ? add(value.intValue) : add(value.itemValue);
    }

    /**
     * Multiply the given value with the Item's worry level
     * @param value a worry level value (either Item or integer)
     * @return a new Item with an increased worry level
     */
    default Item multiply(Value value) {
        return value.isInt ? multiply(value.intValue) : multiply(value.itemValue);
    }

    /**
     * Tests if the current worry level is divisible by the given value
     * @param value an integer value
     * @return true if the worry level is divisible by value, false otherwise
     */
    boolean isDivisibleBy(int value);

    /**
     * Apply the relief to the worry level following a monkey's inspection, if relevant
     * @return a new Item with a decreased (or not) worry level
     */
    Item applyRelief();
}
