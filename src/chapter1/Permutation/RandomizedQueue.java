package chapter1.Permutation;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] ar;
    private int n;

    public RandomizedQueue() {
        ar = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == ar.length - 1) {
            Item[] z = (Item[]) new Object[ar.length * 2];
            for (int i = 0; i < n; i++) {
                z[i] = ar[i];
            }
            ar = z;
        }

        ar[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int p = StdRandom.uniform(n);
        Item tmp = ar[p];
        ar[p] = ar[--n];
        ar[n] = null;

        if (4 * n < ar.length && n > 0) {
            Item[] z = (Item[]) new Object[ar.length / 2];
            for (int i = 0; i < n; i++) {
                z[i] = ar[i];
            }
            ar = z;
        }
        return tmp;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int p = StdRandom.uniform(n);
        return ar[p];
    }

    public Iterator<Item> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<Item> {
        private int len;
        private Item[] a;

        public MyListIterator() {
            len = n;
            a = (Item[]) new Object[len];
            for (int i = 0; i < len; i++) {
                a[i] = ar[i];
            }
        }

        public boolean hasNext() {
            return len > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            int p = StdRandom.uniform(len);
            Item tmp = a[p];
            a[p] = a[--len];
            a[len] = null;
            return tmp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 3; i++) {
            queue.enqueue(i);
        }
        System.out.println("Iterator 1:");
        for (int i : queue) {
            System.out.println(i);
        }
        System.out.println("Iterator 2:");
        for (int i : queue) {
            System.out.println(i);
        }
    }
}
