package ToggleGame.backend;

/**
 * Queue data structure class.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @version 1.0 03/09/23
 * @see <a href="https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Queue.java.html">Queue</a>
 */
public class Queue<Item> {
    /**
     * Front of queue.
     */
    private Node<Item> first;

    /**
     * End of queue.
     */
    private Node<Item> last;

    /**
     * Number of items in the queue.
     */
    private int n;

    /**
     * Helper class for linked list structure.
     */
    private static class Node<Item> {
        /**
         * Data item of node.
         */
        Item item;

        /**
         * Next node link.
         */
        Node<Item> next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * Returns true if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the queue.
     *
     * @return the number of items in the game.
     */
    public int size() {
        return n;
    }

    /**
     * Adds an item to the end of the queue.
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    /**
     * Removes and returns the item at the front of the queue.
     *
     * @return the item at the front of the queue
     */
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
}
