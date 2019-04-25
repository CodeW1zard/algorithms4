import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private boolean isSolvable;
    private SearchNode endNode;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode prevNode;
        private int priority;
        private int moves;

        SearchNode(Board currBoard, SearchNode prevNode, int moves) {
            this.board = currBoard;
            this.moves = moves;
            this.priority = currBoard.manhattan() + moves;
            this.prevNode = prevNode;
        }


        public int compareTo(SearchNode that) {
            return Integer.compare(this.priority, that.priority);
        }
    }

    public Solver(Board initial) {

        if (initial == null) throw new IllegalArgumentException();
        MinPQ<SearchNode> pq = new MinPQ<>();
        SearchNode initialNode = new SearchNode(initial, null, 0);
        pq.insert(initialNode);

        Board twinInitial = initial.twin();
        MinPQ<SearchNode> twinPq = new MinPQ<>();
        SearchNode twinInitialNode = new SearchNode(twinInitial, null, 0);
        twinPq.insert(twinInitialNode);

        while (!pq.min().board.isGoal() && !twinPq.min().board.isGoal()) {
            SearchNode node = pq.delMin();
            for (Board adj : node.board.neighbors()) {
                if (adj.equals(node.prevNode.board)) continue;
                SearchNode tmp = new SearchNode(adj, node, node.moves + 1);
                pq.insert(tmp);
            }


            SearchNode twinNode = twinPq.delMin();
            for (Board adj : twinNode.board.neighbors()) {
                if (adj.equals(node.prevNode.board)) continue;
                SearchNode tmp = new SearchNode(adj, twinNode, twinNode.moves + 1);
                twinPq.insert(tmp);
            }
        }

        this.endNode = pq.min();
        this.isSolvable = this.endNode.board.isGoal();
    }

    public boolean isSolvable() {
        return this.isSolvable;
    }

    public int moves() {
        return endNode.moves;
    }

    public Iterable<Board> solution() {
        Stack<Board> solution = new Stack<>();
        while (endNode != null) {
            solution.push(endNode.board);
            endNode = endNode.prevNode;
        }
        return solution;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
