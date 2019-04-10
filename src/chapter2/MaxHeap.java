package chapter2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MaxHeap<Key extends Comparable> {
    private Key[] hp;
    private int N;
    private final int init = 2;

    public MaxHeap() {
        hp = (Key[]) new Comparable[init];
    }

    public void insert(Key val) {
        if (N == hp.length) {
            Key[] hp_ = (Key[]) new Comparable[2 * N];
            for (int i = 0; i < N; i++) {
                hp_[i] = hp[i];
            }
            hp = hp_;
        }
        hp[N++] = val;
        swim(N);
    }

    public Key delMax() {
        if (N == hp.length / 4 && N > init) {
            Key[] hp_ = (Key[]) new Comparable[hp.length / 2];
            for (int i = 0; i < N; i++) {
                hp_[i] = hp[i];
            }
            hp = hp_;
        }
        if (isEmpty()) throw new IndexOutOfBoundsException();
        Key val = hp[0];
        exch(N--, 1);
        sink(1);
        hp[N] = null;
        return val;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j + 1 <= N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return hp[i - 1].compareTo(hp[j - 1]) < 0;
    }

    private void exch(int i, int j) {
        Key tmp = hp[i - 1];
        hp[i - 1] = hp[j - 1];
        hp[j - 1] = tmp;
    }

    public static void main(String[] args) {
        MaxHeap<String> pq = new MaxHeap<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }


}
