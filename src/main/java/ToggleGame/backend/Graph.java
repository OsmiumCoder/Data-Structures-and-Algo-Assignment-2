package ToggleGame.backend;

/**
 * Class to represent an undirected graph.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Jonathon Meney
 * @version 1.0 03/09/23
 * @see <a href="https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Graph.java.html">Graph</a>
 */
public class Graph {
    /**
     * Number of vertices in the graph.
     */
    private final int V;

    /**
     * Number of edges in the graph.
     */
    private int E;

    /**
     * An array of bags representing each vertex that each hold the vertices adjacent to it.
     */
    private final Bag<Integer>[] adj;

    /**
     * Initializes a new graph.
     *
     * @param V the number of vertices in the graph
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices in the graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return the number of edges in the graph
     */
    public int E() {
        return E;
    }

    /**
     * Adds an edge between two given vertices.
     *
     * @param v a vertex to connect
     * @param w another vertex to connect to the first
     */
    public void addEdge(int v, int w) {
        // to save space check if the two vertices are already adjacent
        // also saves time later in path find
        for (Integer vertex : adj[v]) {
            if (vertex.equals(w)) {
                return;
            }
        }
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * Returns the vertices adjacent to the given vertex.
     *
     * @param v the vertex to get the adjacent vertices of
     * @return the vertices adjacent to v
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
