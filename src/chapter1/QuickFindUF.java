package chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int n) {
        this.id = new int[n];
        for (int i = 0; i < this.id.length; i++) {
            this.id[i] = i;
        }
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int find(int p) {
        return this.id[p];
    }

    int count() {
        return this.id.length;
    }

    void union(int p, int q) {
        int val = this.id[p];
        for (int i = 0; i < this.id.length; i++) {
            if (this.id[i] == val) {
                this.id[i] = this.id[q];
            }
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
