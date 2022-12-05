package aoc2022.day05;

import java.util.NoSuchElementException;


public class FastTransferStack<T> extends Stack<T> {


    private Node<T> top = null;

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (top != null) {
            top.previous = newNode;
            newNode.next = top;
        }
        top = newNode;
    }

    @Override
    public T pop() {
        T removed = peek();
        if (top.next != null) {
            top.previous = null;
        }
        top = top.next;
        return removed;
    }

    @Override
    public T peek() {
        if (top == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        return top.value;
    }

    @Override
    public Stack<T> copy() {
        FastTransferStack<T> copy = new FastTransferStack<>();
        Node<T> current = top;
        Node<T> previous = null;
        while (current != null) {
            Node<T> newNode = new Node<>(current.value);
            if (previous == null) {
                copy.top = newNode;
            } else {
                previous.next = newNode;
                newNode.previous = previous;
            }
            previous = newNode;
            current = current.next;
        }

        return copy;
    }

    @Override
    public void transferTo(Stack<T> otherStack, int amount, boolean reverse) {
        if (amount > 0) {
            if (otherStack instanceof FastTransferStack) {
                FastTransferStack<T> other = (FastTransferStack<T>) otherStack;

                Node<T> bottom = top;
                Node<T> next = top;

                for (int i = 0; i<amount; ++i) {
                    bottom = next;
                    if (bottom == null) {
                        throw new NoSuchElementException("Not enough elements in this stack");
                    }
                    next = bottom.next;

                    if (reverse) {
                        bottom.reverse();
                    }
                }

                Node<T> newTop;
                Node<T> newBottom;

                if (reverse) {
                    newTop = bottom;
                    newBottom = top;
                } else {
                    newTop = top;
                    newBottom = bottom;
                }

                if (other.top != null) {
                    other.top.previous = newBottom;
                }
                newBottom.next = other.top;
                other.top = newTop;

                top = next;
                if (top != null) {
                    top.previous = null;
                }
            } else {
                super.transferTo(otherStack, amount, reverse);
            }
        }

    }

    private static class Node<T> {
        final T value;
        Node<T> next = null;
        Node<T> previous = null;

        Node(T value) {
            this.value = value;
        }

        void reverse() {
            Node<T> tmp = next;
            next = previous;
            previous = tmp;
        }
    }
}
