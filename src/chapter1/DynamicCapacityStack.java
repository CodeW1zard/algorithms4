package chapter1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class DynamicCapacityStack<Item> implements Iterable<Item> {
    private Item[] s;
    private int N;
    private int LO = 100;

    public Iterator<Item> iterator() {
        return new ReverseArrayIterable();
    }

    private class ReverseArrayIterable implements Iterator<Item> {
        private int ind;

        public ReverseArrayIterable() {
            ind = N - 1;
        }

        public boolean hasNext() {
            return ind >= 0;
        }

        public Item next() {
            if (!hasNext()) throw new IndexOutOfBoundsException("index out of bounds");
            return s[ind--];
        }

        public void remove() {
            throw new UnsupportedOperationException("the remove method is not supported");
        }
    }

    public DynamicCapacityStack() {
        s = (Item[]) new Object[LO];
        N = 0;
    }

    public void push(Item item) {
        resize();
        s[N++] = item;
    }

    public Item pop() {
        resize();
        Item item = s[--N];
        s[N] = null;
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void resize() {
        if (N == s.length) {
            Item[] z = (Item[]) new Object[2 * s.length];
            for (int i = 0; i < s.length; i++) {
                z[i] = s[i];
            }
            s = z;
        } else if (N < s.length / 4 && s.length >= 2 * LO) {
            Item[] z = (Item[]) new Object[s.length / 2];
            for (int i = 0; i <= N; i++) {
                z[i] = s[i];
            }
            s = z;
        }

    }

    public static void main(String[] args) {
        DynamicCapacityStack<String> stack = new DynamicCapacityStack<>();
        for (int i = 0; i < 50; i++) {
            stack.push(String.format("push %d", i));
            StdOut.println(String.format("push: %d", i));
        }
        for (String s : stack) {
            StdOut.println("iter: " + s);
        }
        for (int i = 0; i < 50; i++) {
            String s = stack.pop();
            StdOut.println(String.format("pop: %d", i) + " " + s);
        }

    }

}
