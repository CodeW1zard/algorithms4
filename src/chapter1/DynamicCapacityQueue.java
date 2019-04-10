package chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicCapacityQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int lo, hi;

    public DynamicCapacityQueue() {
        a = (Item[]) new Object[2];
        lo = 0;
        hi = 0;
    }

    public void enqueue(Item item) {
        if (hi == a.length) resize(a.length * 2);
        a[hi++] = item;
    }

    public Item dequeue() {
        if (lo == hi - 1) {
            Item item = a[lo];
            a[lo] = null; // avoid loitering
            lo = 0;
            hi = 0;
            return item;
        } else if (!isEmpty()) throw new NoSuchElementException();
        if (hi - lo < a.length / 4 && hi - lo > 0) resize(a.length / 2);
        Item item = a[lo];
        a[lo++] = null;   // avoid loitering
        return item;
    }


    public void resize(int newLength) {
        Item[] b = (Item[]) new Object[newLength];
        for (int i = 0; i < hi - lo; i++) {
            b[i] = a[lo + i];
        }
        hi = hi - lo;
        lo = 0;
        a = b;
    }

    public boolean isEmpty() {
        return lo < hi;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayList();
    }

    private class ReverseArrayList implements Iterator<Item> {
        int p;

        public ReverseArrayList() {
            p = lo;
        }

        public boolean hasNext() {
            return p < hi;
        }

        public Item next() {
            if (!hasNext()) throw new IndexOutOfBoundsException();
            return a[p++];
        }

        public void remove() {
            throw new UnsupportedOperationException("remove function is not supported");
        }
    }

    public static void main(String[] args) {
        DynamicCapacityQueue<Integer> queue = new DynamicCapacityQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println("enqueue: " + i);
            if ((i + 1) % 2 == 0) {
                int k = queue.dequeue();
                System.out.println("dequeue: " + k);
            }
        }

        for (int i : queue) {
            System.out.println(i);
        }

    }
}
