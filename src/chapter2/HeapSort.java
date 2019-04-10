package chapter2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
/*
时间复杂度 2 N logN
空间复杂度 1
利用了最大堆实现
注意我们在用完全二叉树的数据结构时，其性质一般是用1-N编码描述的
应用时要转化乘0-N-1编码
 */
public class HeapSort {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        while (N > 0) {
            exch(a, 0, N - 1);
            sink(a, 1, --N);
        }
    }

    private static void sink(Comparable[] hp, int k, int N) {
        while (2 * k <= N) {
            int next = 2 * k;
            if (next + 1 <= N && less(hp, next - 1, next)) next++;
            if (!less(hp, k - 1, next - 1)) break;
            exch(hp, k - 1, next - 1);
            k = next;
        }
    }


    private static void exch(Comparable[] hp, int i, int j) {
        Comparable tmp = hp[i];
        hp[i] = hp[j];
        hp[j] = tmp;
    }

    private static boolean less(Comparable[] hp, int i, int j) {
        return hp[i].compareTo(hp[j]) < 0;
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
//             test sort
            Integer[] rnds_copy = new Integer[d];
            System.arraycopy(rnds, 0, rnds_copy, 0, d);
            sort(rnds);
            Arrays.sort(rnds_copy);
            if (Arrays.equals(rnds, rnds_copy)) {
                cnt++;
            }
        }
        StdOut.println(Arrays.toString(rnds));
        StdOut.println("cnt/N = " + cnt + "/" + N);
    }
}

