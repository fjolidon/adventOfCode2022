package aoc2022.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;


public class Monkeys {

    private final LinkedList<MonkeyParam> params = new LinkedList<>();
    private Monkey[] monkeys;
    private final String[] inputLines = new String[MonkeyParam.INPUT_SIZE];
    private int inputLinesIndex = 0;
    private int round = 0;
    private final BiFunction<Integer, Set<Integer>, Item> itemConstructor;

    public Monkeys(BiFunction<Integer, Set<Integer>, Item> itemConstructor) {
        this.itemConstructor = itemConstructor;
    }

    private Monkeys(BiFunction<Integer, Set<Integer>, Item> itemConstructor, LinkedList<MonkeyParam> params) {
        this(itemConstructor);
        this.params.addAll(params);
        finalizeInput();
    }

    public void processInput(String line) {
        if (!line.isEmpty()) {
            inputLines[inputLinesIndex++] = line.trim();
            if (inputLinesIndex == MonkeyParam.INPUT_SIZE) {
                params.add(new MonkeyParam(inputLines));
                inputLinesIndex = 0;
            }
        }
    }

    public void finalizeInput() {
        monkeys = new Monkey[params.size()];
        TreeSet<Integer> modBases = new TreeSet<>();

        int i=0;
        for (MonkeyParam param : params) {
            monkeys[i] = new Monkey(i, itemConstructor);
            modBases.add(param.modBase);
            ++i;
        }

        for (MonkeyParam param : params) {
            monkeys[param.id].init(
                    param.startingItems,
                    param.operation,
                    param.test,
                    monkeys[param.throwIfTrue],
                    monkeys[param.throwIfFalse],
                    modBases);
        }
    }

    public void processRound() {
        for (Monkey monkey : monkeys) {
            monkey.processRound();
        }
        ++round;

//        System.out.println("After round " + round);
//        for (Monkey monkey : monkeys) {
//            System.out.println(monkey);
//        }
//        System.out.println();

    }

    public void processRounds(int n) {
        for (int i = 0; i < n; ++i) {
            processRound();
        }
    }

    public long monkeyBusiness() {
        ArrayList<Monkey> monkeyByActivity = new ArrayList<>(monkeys.length);

        Collections.addAll(monkeyByActivity, monkeys);
        monkeyByActivity.sort(Collections.reverseOrder());

        return monkeyByActivity.get(0).getInspectCount()
                * monkeyByActivity.get(1).getInspectCount();
    }


    public Monkeys copy(BiFunction<Integer, Set<Integer>, Item> itemConstructor) {
        return new Monkeys(itemConstructor, params);
    }


}
