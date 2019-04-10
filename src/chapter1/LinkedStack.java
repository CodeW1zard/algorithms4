package chapter1;

import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item>{
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public void push(Item value) {
        Node node = first;
        first = new Node();
        first.item = value;
        first.next = node;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isNull() {
        return first == null;
    }

    public Iterator<Item> iterator(){
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<Item> {
        private Node node;


        public LinkedListIterator() {
            node = first;
        }

        public boolean hasNext() {
            return node!=null;
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
}
