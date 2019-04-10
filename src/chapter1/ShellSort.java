package chapter1;

import edu.princeton.cs.algs4.StdOut;

public class ShellSort {
    /*
    Shell Sort利用了Insertion Sort的性质，即对于partially sorted array
    具有线性的复杂度。为了构造这样的array，shell sort采取对间隔的子列分别进行
    insertion sort的方法，间隔由高到低，最后一个为1。

    increment的选取比较重要，比较好的是3*x+1这个序列
    切记1 2 4 8 16不好，因为在increment=1之前，不能对奇偶数位的元素混合进行排序
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        if (N <= 1) return;
        int incre = 1;

//        the sequence of increment 1, 4, 13, ...
        while (incre < N) incre = 3 * incre + 1;
        while (incre >= 1) {
            for (int i = incre; i < N; i++) {
                for (int j = i; j >= incre; j -= incre) {
                    if (less(a[j], a[j - incre])) {
                        exch(a, j, j - incre);
                    } else {
                        break;
                    }
                }
            }
            incre /= 3;
        }
    }

    public static boolean less(Comparable x, Comparable y) {
        return x.compareTo(y) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void show(Comparable[] a) {
        for (Comparable i : a) {
            StdOut.println(i);
        }
    }

    public static void main(String[] args) {
        String[] a = {"8", "7", "6", "5", "4", "3", "9", "1"};
        sort(a);
        show(a);
    }
}
