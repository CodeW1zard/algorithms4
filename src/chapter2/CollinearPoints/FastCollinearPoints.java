package chapter2.CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> segs = new ArrayList<>();
    private Point[] points;

    public FastCollinearPoints(Point[] pointsIn) {
        // check null input
        if (pointsIn == null) throw new IllegalArgumentException();
        for (Point p : pointsIn) if (p == null) throw new IllegalArgumentException();
        // copy to points
        int N = pointsIn.length;
        points = new Point[N];
        for (int i = 0; i < N; i++) points[i] = pointsIn[i];
        // check duplicates
        Arrays.sort(points);
        for (int i = 0; i < N - 1; i++) {
            if (points[i].equals(points[i + 1])) throw new IllegalArgumentException();
        }
        // exclude short length points
        if (N < 4) return;
        // to put collinear points
        List<Point> collinearPoints = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Point origin = pointsIn[i];
            // after sort, points[0]==origin
            Arrays.sort(points, origin.slopeOrder());
            // get the initial slope
            collinearPoints.clear();
            collinearPoints.add(points[1]);
            double slope = origin.slopeTo(points[1]);
            for (int j = 2; j < N && j + 3 - collinearPoints.size() <= N; j++) {
                Point p = points[j];
                if (origin.slopeTo(p) == slope) {
                    collinearPoints.add(p);
                    if (j == N - 1 && collinearPoints.size() > 2) {
                        collinearPoints.add(origin);
                        Collections.sort(collinearPoints);
                        if (collinearPoints.get(0).compareTo(origin) == 0) {
                            Point end = collinearPoints.get(collinearPoints.size() - 1);
                            segs.add(new LineSegment(origin, end));
                        }
                    }
                } else {
                    if (collinearPoints.size() > 2) {
                        collinearPoints.add(origin);
                        Collections.sort(collinearPoints);
                        if (collinearPoints.get(0).compareTo(origin) == 0) {
                            Point end = collinearPoints.get(collinearPoints.size() - 1);
                            segs.add(new LineSegment(origin, end));
                        }
                    }
                    collinearPoints.clear();
                    collinearPoints.add(p);
                    slope = origin.slopeTo(p);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
