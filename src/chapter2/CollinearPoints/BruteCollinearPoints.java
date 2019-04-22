package chapter2.CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private List<LineSegment> segs = new ArrayList<>();
    private Point[] points;

    public BruteCollinearPoints(Point[] pointsIn) {
        // check null input
        if (pointsIn == null) throw new IllegalArgumentException();
        for(Point p:pointsIn) if(p==null) throw new IllegalArgumentException();
        // copy to points
        int N = pointsIn.length;
        points = new Point[N];
        for(int i=0; i<N; i++) points[i] = pointsIn[i];
        // check duplicates
        Arrays.sort(points);
        for (int i = 0; i < N-1; i++) {
            if(points[i].equals(points[i+1])) throw new IllegalArgumentException();
        }

        for (int i = 0; i < N - 3; i++) {
            Point p = points[i];
            for (int j = i + 1; j < N - 2; j++) {
                Point q = points[j];
                double slopeQ = p.slopeTo(q);
                for (int s = j + 1; s < N - 1; s++) {
                    Point r = points[s];
                    double slopeR = p.slopeTo(r);
                    if (slopeQ != slopeR) continue;
                    for (int t = s + 1; t < N; t++) {
                        Point e = points[t];
                        double slopeE = p.slopeTo(e);
                        if (slopeR == slopeE) {
                            segs.add(new LineSegment(p, e));
                        }
                    }
                }

            }
        }
    }

    public int numberOfSegments() {
        return segs.size();
    }

    public LineSegment[] segments() {
        LineSegment[] res = new LineSegment[this.numberOfSegments()];
        this.segs.toArray(res);
        return res;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
