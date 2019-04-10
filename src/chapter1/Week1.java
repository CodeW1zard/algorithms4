package chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Week1 {
    public static void main(String[] args) {
//        StdOut.println(whenIsFullyConnected());
//        testFindMax();
        testEvaluator();
    }

    public static int whenIsFullyConnected() {
        int N = StdIn.readInt();
        int count = 0;
        int p, q;
        SocialNetwork net = new SocialNetwork(N);
        while (!StdIn.isEmpty()) {
            count++;

            p = StdIn.readInt();
            q = StdIn.readInt();
            p = net.root(p);
            q = net.root(q);
            if (p != q) {
                net.union(p, q);
            }
            if (net.weight[p] == N || net.weight[q] == N) return count;
        }
        return -1;
    }

    public static void testFindMax() {
        int N = StdIn.readInt();
        CanonicalElement ce = new CanonicalElement(N);
        int maxConnect = 5;
        int i = 0;
        while (!StdIn.isEmpty()) {
            i++;
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!ce.connected(p, q)) {
                ce.union(p, q);
            }
            if (i > maxConnect) break;
        }
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            StdOut.println(ce.findMax(p));
        }
    }

    public static void testEvaluator(){
        Evaluator evaluator = new Evaluator();
        while(StdIn.hasNextLine()){
            char[] s = StdIn.readString().toCharArray();
            double res = evaluator.evaluate(s);
            StdOut.println("= " +  res);

        }
    }
}

class SocialNetwork {
    int[] id;
    int[] weight;

    SocialNetwork(int N) {
        id = new int[N];
        weight = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            weight[i] = 1;
        }
    }

    int root(int p) {
        int node = p;
        int tmp;
        while (p != id[p]) {
            p = id[p];
        }
        while (node != p) {
            tmp = node;
            node = id[node];
            id[tmp] = p;
        }
        return p;
    }

    void union(int root1, int root2) {
        if (root1 == root2) return;
        if (weight[root1] > weight[root2]) {
            id[root2] = root1;
            weight[root1] += weight[root2];
        } else {
            id[root1] = root2;
            weight[root2] += weight[root1];
        }
    }
}

class CanonicalElement {
    int[] id;
    int[] weight;
    int[] max;

    CanonicalElement(int N) {
        id = new int[N];
        weight = new int[N];
        max = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            weight[i] = 1;
            max[i] = i;
        }

    }

    public int find(int p) {
        int q = p;
        while (p != id[p]) p = id[p];

        int i;
        while (q != id[q]) {
            i = q;
            q = id[q];
            id[i] = p;
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (weight[i] > weight[j]) {
            weight[i] += weight[j];
            id[j] = i;
            max[i] = max[i] > max[j] ? max[i] : max[j];
        } else {
            weight[j] += weight[j];
            id[i] = j;
            max[j] = max[i] > max[j] ? max[i] : max[j];
        }
    }

    public int findMax(int p) {
        return max[find(p)];
    }
}

class Evaluator{
    DynamicCapacityStack<Character> ops = new DynamicCapacityStack<>();
    DynamicCapacityStack<Double> nums = new DynamicCapacityStack<>();

    double evaluate(char[] s){
        for(char c:s){
            if(c<='9' && c>='0'){
                nums.push((double)Character.getNumericValue(c));
            }else if(c=='('){
                continue;
            }else if(c==')'){
                double x = nums.pop();
                double y = nums.pop();
                char op = ops.pop();
                nums.push(operate(y, x, op));
            }else{
                ops.push(c);
            }
        }
        return nums.pop();
    }

    double operate(double x, double y, char op){
        double res;
        if(op == '*'){
            res = x * y;
        }else if(op == '-'){
            res = x - y;
        }else if(op == '+'){
            res = x + y;
        }else if(op == '/'){
            res = x/y;
        }else if(op =='^'){
            res = Math.pow(x, y);
        }else{
            throw new UnsupportedOperationException("unsupported "+op);
        }
        return res;
    }


}