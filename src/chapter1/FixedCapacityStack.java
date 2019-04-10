package chapter1;

import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item>{
    private Item[] s;
    private int N;

    public FixedCapacityStack(int capacity) {
        s = (Item[]) new Object[capacity];
        N = 0;
    }

    public void push(Item s) {
        this.s[N++] = s;
    }

    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Iterator<Item> iterator(){
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<Item> {
        private int ind;

        public ReverseIterator() {
            ind = N - 1;
        }

        public boolean hasNext() {
            return ind >= 0;
        }

        public Item next() {
            if (!hasNext()) throw new IndexOutOfBoundsException("index out of bounds");
            return s[ind--];
        }

        public void remove() {
            throw new UnsupportedOperationException("the remove method is not supported");
        }
    }


    public static void main(String[] args) {
        FixedCapacityStack<String> stack = new FixedCapacityStack<>(10);
        stack.push("Hello");
        stack.push("World");
        stack.isEmpty();
        System.out.println(stack.pop() + " "+ stack.pop());

//        int a = 0;
//        int b = 1;
//        System.out.println("a:"+a);
//        System.out.println("++a"+(++a));
//        System.out.println("a:"+a);
//        System.out.println("a++:"+(a++));
//        System.out.println("a:"+a);
//
//        System.out.println("--a"+(--a));
//        System.out.println("a:"+a);
//        System.out.println("a--:"+(a--));
//        System.out.println("a:"+a);
    }
}
