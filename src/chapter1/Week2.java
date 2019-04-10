package chapter1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Week2 {
    public static void main(String[] args){
        Point[] a = new Point[15];
        Point[] b = new Point[10];
        for(int i=0;i<8;i++){
            b[i] = new Point(StdRandom.uniform(), StdRandom.uniform());
        }
        b[8] = new Point(0.3, 2.1);
        b[9] = new Point(1.2, 43);

        for(int i=0;i<13;i++){
            a[i] = new Point(StdRandom.uniform(), StdRandom.uniform());
        }
        a[13] = b[9];
        a[14] = b[8];

        Q1 q = new Q1();
        StdOut.println(q.count_common_points(a, b));
    }
}


class Point implements Comparable<Point>{
    double x;
    double y;

    Point(){}
    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
//    x优先排序
    public int compareTo(Point p) {
        int res_x = Double.compare(this.x, p.x);
        if(res_x == 0){
            return Double.compare(this.y, p.y);
        }
        return res_x;
    }
}
class Q1{
    void sort(Point[] a){
        ShellSort.sort(a);
    }
    int count_common_points(Point[] a, Point[] b){
        sort(a);
        sort(b);
        int i=0;
        int j=0;
        int cnt = 0;
        while(true){
            int cmp = a[i].compareTo(b[j]);
            boolean incre_i;
            if(cmp == 0){
                cnt++;
                incre_i = true;
            }else if(cmp == -1){
                incre_i = true;
            }else{
                incre_i = false;
            }
            if(i==a.length-1 && j ==b.length-1){
                break;
            }
            if(incre_i && i<a.length-1){
                i++;
            }else if(!incre_i && j<b.length-1){
                j++;
            }else{
                continue;
            }
        }
        return cnt;
    }
}
