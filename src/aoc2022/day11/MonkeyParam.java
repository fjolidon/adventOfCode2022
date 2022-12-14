package aoc2022.day11;

import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MonkeyParam {
    public final int id;
    public int[] startingItems;
    public UnaryOperator<Item> operation;
    public Predicate<Item> test;
    public int throwIfTrue;
    public int throwIfFalse;
    public final int modBase;

    public final static int INPUT_SIZE = 6;

    private final static Pattern MONKEY_PATTERN = Pattern.compile("Monkey (\\d+):");
    private final static Pattern STARTING_ITEMS = Pattern.compile("Starting items: ((\\d+,? ?)+)");
    private final static Pattern OPERATION = Pattern.compile("Operation: new = (.*)");
    private final static Pattern TEST = Pattern.compile("Test: divisible by (\\d+)");
    private final static String THROW_STRING = "If %s: throw to monkey (\\d+)";
    private final static Pattern THROW_TRUE = Pattern.compile(String.format(THROW_STRING, "true"));
    private final static Pattern THROW_FALSE = Pattern.compile(String.format(THROW_STRING, "false"));

    private final static Pattern PARSE_OPERATION = Pattern.compile("old ((\\*)|(\\+)) ((old)|(\\d+))");

    private final static Pattern[] PATTERNS = {
            MONKEY_PATTERN,
            STARTING_ITEMS,
            OPERATION,
            TEST,
            THROW_TRUE,
            THROW_FALSE,
    };

    public MonkeyParam(String[] input) {
        String[] parsedInputs = new String[INPUT_SIZE];

        for (int i = 0; i < INPUT_SIZE; ++i) {
            Matcher m = PATTERNS[i].matcher(input[i]);
            if (!m.matches()) {
                throw new IllegalArgumentException();
            }
            parsedInputs[i] = m.group(1);
        }

        id = Integer.parseInt(parsedInputs[0]);
        String[] itemsStr = parsedInputs[1].split(", ");
        startingItems = new int[itemsStr.length];
        int i = 0;
        for (String str : itemsStr) {
            startingItems[i++] = Integer.parseInt(str);
        }
        operation = parseOperation(parsedInputs[2]);
        modBase = Integer.parseInt(parsedInputs[3]);
        test = item -> item.isDivisibleBy(modBase);
        throwIfTrue = Integer.parseInt(parsedInputs[4]);
        throwIfFalse = Integer.parseInt(parsedInputs[5]);
    }

    private static UnaryOperator<Item> parseOperation(String operation) {
        Matcher m = PARSE_OPERATION.matcher(operation);
        if (m.matches()) {
            String op = m.group(1);
            BiFunction<Item, Item.Value, Item> operator;
            if (op.equals("*")) {
                operator = Item::multiply;
            } else if (op.equals("+")) {
                operator = Item::add;
            } else {
                throw new IllegalArgumentException("Unknown operator: " + op);
            }

            String y = m.group(4);
            if (y.equals("old")) {
                return old -> operator.apply(old, new Item.Value(old));
            } else {
                Item.Value value = new Item.Value(Integer.parseInt(y));
                return old -> operator.apply(old, value);
            }
        }
        throw new IllegalArgumentException();
    }
}
