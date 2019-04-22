import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    public Solver(Board initial) {
        MinPQ<Board> pq = new MinPQ<>();
        List<Board> solution = new ArrayList<>();
        solution.add(initial);
        pq.insert(initial);
        while (!initial.isGoal()) {
            Board board = pq.delMin();
            for (Board adj : board.neighbors()) {
                pq.insert(adj);
            }
        }

    }

}
