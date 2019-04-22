package chapter1.Percolation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] results;
    private double mean;
    private double stddev;
    private double CONFIDENCE_95 = 1.96;

    public PercolationStats(int n, int trials) {
        if (n < 0) throw new IllegalArgumentException("n should be an integer greater than 0");
        if (trials < 1) throw new IllegalArgumentException("trials should be an integer greater than 0");

        results = new double[trials];
        int total = n * n;
        for (int i = 0; i < trials; i++) {
            Percolation model = new Percolation(n);
            while (!model.percolates()) {
                int rand = StdRandom.uniform(total);
                int row = 1 + rand / n;
                int col = 1 + rand % n;
                model.open(row, col);
            }
            int numOpen = model.numberOfOpenSites();
            results[i] = (double) numOpen / total;
        }
    }

    public double mean() {
        mean = StdStats.mean(results);
        return mean;
    }

    public double stddev() {
        stddev = StdStats.stddev(results);
        return stddev;
    }

    public double confidenceLo() {
        return mean - CONFIDENCE_95 * stddev / Math.sqrt(results.length);
    }

    public double confidenceHi() {
        return mean + CONFIDENCE_95 * stddev / Math.sqrt(results.length);
    }

    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            int n = StdIn.readInt();
            int trial = StdIn.readInt();
            PercolationStats mc = new PercolationStats(n, trial);
            StdOut.println(String.format("mean = %.4f, stddev=%.4f, [%.4f,  %.4f]", mc.mean(),
                    mc.stddev(), mc.confidenceLo(), mc.confidenceHi()));
        }
    }
}
