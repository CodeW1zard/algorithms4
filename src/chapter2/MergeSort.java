package chapter2;

public class MergeSort {
    /*
    时间复杂度NlogN
    空间复杂度N
     */
    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        boolean res = true;
        for (int i = lo; i < hi; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) {
                res = false;
                break;
            }
        }
        return res;
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid) : "left part is not sorted";
        assert isSorted(a, mid + 1, hi) : "right part is not sorted";
//        假如左右两侧已经排好序了，跳过merge
        if (a[mid].compareTo(a[mid + 1]) <= 0) return;

        /*
        有一种trick：不需要将a[lo:hi]的元素copy到aux
        只需要每次sort的时候互换aux与a的角色即可
        即复制的代码然后在sort函数里面：
        sort(aux, a, lo, mid, hi)
         */
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) > 0) a[k] = aux[j++];
            else a[k] = aux[i++];

        }
        assert isSorted(a, lo, hi) : "not sorted";
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi - lo < 1) return;
        int mid = (hi - lo) / 2 + lo;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        /*
        注意一定要预先定义好长度与a等长的aux，不要在递归的时候创建长度为hi-lo+1的aux
        否则会大大增加时间开销
         */
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        String[] a = {"8", "7", "6", "5", "4", "3", "9", "1"};
        MergeSort model = new MergeSort();
        model.sort(a);
        for (String s : a) {
            System.out.println(s);
        }

    }

}
