package aoc2022.day05;

import java.util.LinkedList;


public class BasicStack<T> extends Stack<T> {

    private final LinkedList<T> list = new LinkedList<>();

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public T pop() {
        return list.removeLast();
    }

    @Override
    public T peek() {
        return list.getLast();
    }

    @Override
    public BasicStack<T> copy() {
        BasicStack<T> newInstance = new BasicStack<>();


        newInstance.list.addAll(list);
        return newInstance;
    }
}
