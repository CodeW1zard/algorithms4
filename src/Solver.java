import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private boolean isSolvable;
    private List<Board> solution;
    private int moves;
    public Solver(Board initial) {
        MinPQ<Board> pq = new MinPQ<>();
        solution = new ArrayList<>();
        solution.add(initial);
        pq.insert(initial);
        Board prevBoard = initial;

        Board twinInitial = initial.twin();
        MinPQ<Board> twinPq = new MinPQ<>();
        List<Board> twinSolution = new ArrayList<>();
        solution.add(twinInitial);
        pq.insert(twinInitial);
        Board twinPrevBoard = twinInitial;

        while (!initial.isGoal() && !twinInitial.isGoal()) {

            Board board = pq.delMin();
            solution.add(board);

            for (Board adj : board.neighbors()) {

                pq.insert(adj);
            }
            initial = board;

            Board twinBoard = twinPq.delMin();
            twinSolution.add(twinBoard);

            for (Board adj : twinBoard.neighbors()) {

                twinPq.insert(adj);
            }
            twinInitial = twinBoard;
        }

        isSolvable = initial.isGoal();
        if(!isSolvable){
            solution = null;
        }else{
            moves = solution.size();
        }

    }

    public boolean isSolvable(){
        return this.isSolvable;
    }

    public int moves(){
        return moves;
    }

    public Iterable<Board> solution(){
        return solution;
    }

    public static void main(String[] args){
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
