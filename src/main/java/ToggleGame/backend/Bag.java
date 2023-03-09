package ToggleGame.backend;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class to hold a bag of items.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @version 1.0 03/09/23
 * @see <a href="https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Bag.java.html">Bag</a>
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * Start of the bag.
     */
    private Node<Item> first;

    /**
     * Number of items in the bag.
     */
    private int n;

    /**
     * Helper class for linked list structure.
     *
     * @param <Item> the generic type of each item in the bag
     */
    private static class Node<Item> {
        /**
         * Data item of node.
         */
        private Item item;

        /**
         * Next node link.
         */
        private Node<Item> next;
    }

    /**
     * Initializes an empty bag.
     */
    public Bag() {
        first = null;
        n = 0;
    }

    /**
     * Adds new item to the bag.
     *
     * @param item item to add to the bag
     */
    public void add(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    /**
     * Returns true if the bag is empty.
     *
     * @return true if the bag is empty, false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in the bag.
     *
     * @return the number of items in the bag
     */
    public int size() {
        return n;
    }

    /**
     * Returns an iterator that iterates over the items in this bag in arbitrary order.
     *
     * @return an iterator that iterates over the items in this bag in arbitrary order
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
