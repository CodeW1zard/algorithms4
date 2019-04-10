package chapter1;

public class SelectionSort {
    /*
        每次将剩余元素中的最小值与开头互换
        时间复杂度：n^2
        空间复杂度：1
        只需要交换n次，但是速度慢
     */
    public static void sort(Comparable[] a) {
        int min;
        for (int i = 0; i < a.length; i++) {
            min = i;
            for (int j = i + 1; j < a.length; i++) {
                if (less(a[j], a[i])) {
                    min = j;
                    exch(a, min, i);
                }
            }
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
}
