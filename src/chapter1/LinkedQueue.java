package chapter1;

import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item> {
    Node first, last;

    public void enqueue(Item item) {
        Node node = last;
        last = new Node(item);
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            node.next = last;
        }
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        private Node node;

        public LinkedListIterator() {
            node = first;
        }

        public boolean hasNext() {
            return node != null;
        }

        public Item next() {
            if (!hasNext()) throw new IndexOutOfBoundsException("index out of bounds");
            Item item = node.item;
            node = node.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("the remove method is not supported");
        }
    }

    private class Node {
        Item item;
        Node next;

        Node(Item val) {
            item = val;
        }
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println("enqueue: " + i);
            if ((i + 1) % 2 == 0) {
                int k = queue.dequeue();
                System.out.println("dequeue: " + k);
            }
        }

        for (int i : queue) {
            System.out.println(i);
        }
    }
}

