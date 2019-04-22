import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] blocks;
    private final int n;

    public Board(int[][] blocks) {
        assert blocks != null : "blocks null input";
        assert blocks.length == blocks[0].length : "must be nxn board";
        this.n = blocks.length;
        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        assert this.blocks != null : "block has not been initialized";
        return this.blocks.length;
    }

    public int hamming() {
        assert this.blocks != null : "block has not been initialized";
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int tmp = i * n;
            for (int j = 0; j < n; j++) {
                if (this.blocks[i][j] != tmp + j + 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public int manhattan() {
        assert this.blocks != null : "block has not been initialized";
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.blocks[i][j] == this.n * i + j + 1) continue;
                int row = this.blocks[i][j] / this.n;
                int col = this.blocks[i][j] % this.n;
                dist += Math.abs(row - i) + Math.abs(col - j);
            }
        }
        return dist;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        assert this.blocks != null : "block has not been initialized";
        Board board = new Board(this.blocks);
        int cnt = 0;
        int k = 0;
        int prev_i = 0;
        int prev_j = 0;
        while (true) {
            int i = k / this.n;
            int j = k++ % this.n;
            if (cnt == 2) {
                int tmp = board.blocks[i][j];
                board.blocks[i][j] = board.blocks[prev_i][prev_j];
                board.blocks[prev_i][prev_j] = tmp;
                break;
            }
            if (board.blocks[i][j] == 0) continue;
            prev_i = i;
            prev_j = j;
            cnt++;
        }
        return board;
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.dimension() != n) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != that.blocks[i][j])
                    return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        int k = 0;
        int i = 0;
        int j = 0;
        for (k = 0; k < this.n * this.n; k++) {
            i = k / this.n;
            j = k % this.n;
            if (this.blocks[i][j] == 0) break;
        }
        List<Board> adjs = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            int i1 = i + direction[0];
            int j1 = j + direction[1];
            if (i1 > 0 && i1 < this.n && j1 > 0 && j1 < this.n) {
                int[][] blocks_ = blocks.clone();
                int tmp = blocks_[i][j];
                blocks_[i][j] = blocks_[i1][j1];
                blocks_[i1][j1] = blocks_[i][j];
                adjs.add(new Board(blocks_));
            }
        }
        return adjs;
    }

    public String toString() {
        StringBuffer board = new StringBuffer();
        String separator = " ";
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.n; ++j)
                if (j > 0)
                    board.append(blocks[i][j]);
                else
                    board.append(blocks[i][j]).append(separator);
            board.append("\n");
        }
        return board.toString();
    }

    public static void main(String[] args) {

    }
}
