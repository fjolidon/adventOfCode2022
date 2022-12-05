package aoc2022.day05;

import java.util.LinkedList;


public abstract class Stack<T>  {

    public abstract void add(T element);

    public abstract T pop();

    public abstract T peek();

    public void transferTo(Stack<T> otherStack, int amount, boolean reverse) {
        if (reverse) {
            for (int i=0; i<amount; ++i) {
                otherStack.add(pop());
            }
        } else {
            LinkedList<T> buffer = new LinkedList<>();

            for (int i=0; i<amount; ++i) {
                buffer.add(pop());
            }

            buffer.descendingIterator().forEachRemaining(otherStack::add);
        }
    }

    public final void transferFrom(Stack<T> otherStack, int amount, boolean reverse) {
        otherStack.transferFrom(this, amount, reverse);
    }

    public abstract Stack<T> copy();
}
