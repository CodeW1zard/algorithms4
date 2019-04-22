package chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] ar;
    private int lo = 1;
    private int hi = 1;
    private int n = 0;

    public Deque() {
        ar = (Item[]) new Object[3];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (lo == 0) {
            Item[] z = (Item[]) new Object[ar.length + hi];
            for (int i = 1; i <= n; i++) {
                z[hi + i] = ar[i];
            }
            lo = hi;
            hi *= 2;
            ar = z;
        }
        ar[lo--] = item;
        if (isEmpty()) hi++;
        n++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (hi == ar.length - 1) {
            Item[] z = (Item[]) new Object[ar.length + hi - lo];
            for (int i = 1; i <= n; i++) {
                z[lo + i] = ar[lo + i];
            }
            ar = z;
        }
        ar[hi++] = item;
        if (isEmpty()) lo--;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = ar[++lo];
        ar[lo] = null;
        n--;

        if (isEmpty()) {
            hi = lo;
        }
        if (n > 0 && 4 * n < hi) {
            Item[] z = (Item[]) new Object[ar.length - hi / 2];
            for (int i = 0; i < n; i++) {
                z[lo + i - hi / 2 + 1] = ar[lo + i + 1];
            }
            ar = z;
            lo -= hi / 2;
            hi -= hi / 2;

        }
        return item;

    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = ar[--hi];
        ar[hi] = null;
        n--;

        if (isEmpty()) {
            lo = hi;
        }

        if (n > 0 && 4 * n < ar.length - lo - 1) {
            Item[] z = (Item[]) new Object[ar.length - (ar.length - lo - 1) / 2];
            for (int i = 1; i <= n; i++) {
                z[lo + i] = ar[lo + i];
            }
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<Item> {
        private int first;
        private int last;

        MyListIterator() {
            if (isEmpty()) {
                first = last = lo;
            } else {
                first = lo + 1;
                last = hi;
            }
        }

        public boolean hasNext() {
            return first < last;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = ar[first++];
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
//        chapter1.Deque<Integer> deque = new chapter1.Deque<>();
//        deque.addLast(1);
//        deque.addLast(2);
//        System.out.println(deque.removeLast());
//        System.out.println(deque.removeLast());
        Deque<String> deque = new Deque<>();
        for (int i = 0; i < 5; i++) {
            deque.addFirst(String.format("added at front: %d", i));
        }
        System.out.println("**************************");
        for (String s : deque) {
            System.out.println(s);
        }
        System.out.println("**************************");
        for (int i = 0; i < 5; i++) {
            deque.addLast(String.format("added at end: %d", i + 5));
        }

        System.out.println("**************************");
        for (String s : deque) {
            System.out.println(s);
        }
        System.out.println("**************************");
        for (int i = 0; i < 5; i++) {
            System.out.println("node " + deque.removeLast() + " removed at end");
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("node " + deque.removeFirst() + " removed at front");
        }
    }

}
