package chapter1;

public class InsertionSort {
    /*
        Insertion Sort作交换的次数等于序列的逆序数
        所以对于逆序数为O(N)级别的partially sorted的序列
        其时间复杂度是linear的，但是最坏的情况即全部逆序，
        复杂度是N^2

        空间复杂度1
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, i);
                } else {
                    break;
                }
            }
        }
    }

    public static boolean less(Comparable x, Comparable y) {
        return x.compareTo(y) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
