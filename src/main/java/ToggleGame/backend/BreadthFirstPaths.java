package ToggleGame.backend;

import java.util.Stack;

/**
 * Class to determine the shortest path between two vertices in a graph.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @version 1.0 03/09/23
 * @see <a href="https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BreadthFirstPaths.java.html">Breadth First Search</a>
 */
public class BreadthFirstPaths {
    /**
     * Used to determine if there is a path from source to vertex.
     */
    private final boolean[] marked;

    /**
     * Used to get the previous edge on the shortest path from source to vertex.
     */
    private final int[] edgeTo;

    /**
     * The source vertex of the Breadth-First-Search.
     */
    private final int s;

    /**
     * Initializes a new Breadth-First-Search.
     * @param G the graph to search
     * @param s the source vertex
     */
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    /**
     * Compute Breadth-First-Search from source vertex.
     *
     * @param G the graph to search
     * @param s the source vertex
     */
    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    /**
     * Returns true if there is a path from the source vertex to a given vertex.
     *
     * @param v vertex to determine if a path exists to
     * @return true if a path exists, false otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Returns the shortest path between the source vertex and some other vertex.
     *
     * @param v vertex to find the shortest path to
     * @return the sequence of vertices that is the shortest path, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
         path.push(s);
        return path;
    }
}
