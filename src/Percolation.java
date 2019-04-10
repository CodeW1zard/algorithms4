import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //    0: blocked
//    1: open
    private WeightedQuickUnionUF uf;
    private boolean[][] sites;
    private int size;
    private int total;
    private int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n should be an integer greater than 0");
//        add two virtual nodes start and end
//        start node is n*n while the end node is n*n+1

        size = n;
        total = size * size + 2;
        uf = new WeightedQuickUnionUF(total);
        sites = new boolean[n][n];
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException(String.format("coordinates [%d, %d] out of range %d", row, col, size));
        row--;
        col--;
        sites[row][col] = true;
        int id = row * size + col;
        for (int k = 0; k < 4; k++) {
            int i = row + directions[k][0];
            int j = col + directions[k][1];
            if (j > -1 && j < size) {
                if (i > -1 && i < size && isOpen(i + 1, j + 1)) {
                    uf.union(i * size + j, id);
                } else if (i == -1) {
                    uf.union(total - 2, id);
//                    didn't figure out how to avoid backwash
                } else if (i == size && !percolates()) {
                    uf.union(total - 1, id);
                }
            }

        }

    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException(String.format("coordinates [%d, %d] out of range %d", row, col, size));
        row--;
        col--;
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException(String.format("coordinates [%d, %d] out of range %d", row, col, size));
        if (!isOpen(row, col)) return false;
        row--;
        col--;
        return uf.connected(total - 2, row * size + col);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                count += sites[i][j] ? 1 : 0;
            }
        }
        return count;
    }

    public boolean percolates() {
        return uf.connected(total - 2, total - 1);
    }

    public static void main(String[] args) {
        StdOut.println("main function in Percolation.java");
        int n = StdIn.readInt();
        Percolation model = new Percolation(n);
        int count = 0;
        while (count < 2 * n) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!model.isOpen(p, q)) {
                model.open(p, q);
                System.out.println(String.format("(%d, %d) is opened, %d in total %b",
                        p, q, model.numberOfOpenSites(), model.percolates()));
            }
            count++;
        }
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            System.out.println(String.format("(%d, %d) is full? %b", p, q, model.isFull(p, q)));

        }
    }

}
