package chapter2;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickSort {
    private QuickSort() {
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // 经过优化的快排：
    // 可以对抗多重复值的情况
    // 设置lt, gt为界限，lt之前都小于pivot，gt之后都大于pivot
    // 遍历序列，如果当前值小于pivot，将其和lt互换，lt和i自增
    // 如果当前值大于piovt，将其和gt互换，gt自减，i不变
    // 如果等于，i自增
    // 另外这里还用了取三数中位数为Pivot的trick
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo, gt = hi;
        int i = lo;
        Comparable pivot = getPivot(a, lo, hi);
        while (i <= gt) {
            if (less(a[i], pivot)) {
                exch(a, i++, lt++);
            } else if (less(pivot, a[i])) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static Comparable getPivot(Comparable[] a, int lo, int hi) {
        int m = lo + (hi - lo) / 2;
        if (less(a[lo], a[m])) {
            if (less(a[m], a[hi])) {
                exch(a, lo, m);
            } else if (less(a[lo], a[hi])) {
                exch(a, lo, hi);
            }
        } else {
            if (less(a[m], a[hi])) {
                exch(a, m, lo);
            } else if (less(a[lo], a[hi])) {
                exch(a, lo, hi);
            }
        }
        return a[lo];
    }

    //    没有经过优化的快排（如果有大量重复数据会很慢）
//    private static void sort(Comparable[] a, int lo, int hi) {
//        if (lo >= hi) return;
//        int p = partition(a, lo, hi);
//        sort(a, lo, p - 1);
//        sort(a, p + 1, hi);
//    }
//
    // 第一种partition
    // 把第一个作为pivot，两个指针从首尾部分别遍历
    // 首指针向前移动直到其值大于等于pivot
    // 尾指针向后移动直到其值小于等于pivot
    // swap首尾指针的值
    // 重复以上步骤直到首位指针相遇
    // 最后将pivot和首指针互换
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i >= hi) break;
            }
            while (less(a[lo], a[--j])) {
                if (j <= lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static Comparable search(Comparable[] a, int k) {
        assert k >= 0 && k < a.length : "k out of range";
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length-1;
        while (lo < hi) {
            int p = partition(a, lo, hi);
            if (p > k) {
                hi = p - 1;
            } else if(p < k) {
                lo = p + 1;
            }else{
                return a[p];
            }
        }
        return a[lo];
    }

    private static boolean less(Comparable x, Comparable y) {
        return x.compareTo(y) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int cnt = 0;
        int N = 3;
        int d = 10;
        int range = 10;
        Integer[] rnds = new Integer[d];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < d; j++) {
                rnds[j] = StdRandom.uniform(0, range);
            }
            // test sort
//            Integer[] rnds_copy = new Integer[d];
//            System.arraycopy(rnds, 0, rnds_copy, 0, d);
//            sort(rnds);
//            Arrays.sort(rnds_copy);
//            if (Arrays.equals(rnds, rnds_copy)) {
//                cnt++;
//            }
            // test search
            int k = StdRandom.uniform(0, d-1);
            Comparable res = search(rnds, k);
            Comparable res1 = Quick.select(rnds, k);
            if (res.compareTo(res1) == 0) cnt += 1;
        }
        StdOut.println(Arrays.toString(rnds));
        StdOut.println("cnt/N = " + cnt + "/" + N);
    }
}
