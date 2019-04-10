package chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUF extends QuickUnionUF {

    int[] id;
    int[] weight;

    public QuickUF() {
    }

    public QuickUF(int N) {
        id = new int[N];
        weight = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            weight[i] = 1;
        }
    }
/*
    这是路径压缩的一种，一次遍历，深度减半
    @Override
    public int find(int p){
        while(p!=this.id[p]){
            this.id[p] = this.id[this.id[p]];
            p = this.id[p];
        }
        return p;
    }
*/

    // 另一种路径压缩，两次循环，直接将路径上的结点压缩至根节点
    @Override
    public int find(int p) {
        int q = p;
        while (p != id[p]) p = id[p];

        int i;
        while (q != id[q]) {
            i = q;
            q = id[q];
            id[i] = p;
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (this.weight[rootP] > this.weight[rootQ]) {
            this.id[rootQ] = this.id[rootP];
            this.weight[rootP] += this.weight[rootQ];
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUF uf = new QuickUF(N);
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
