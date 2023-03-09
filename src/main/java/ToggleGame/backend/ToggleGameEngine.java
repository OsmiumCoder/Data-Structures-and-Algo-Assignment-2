package ToggleGame.backend;

import java.util.ArrayList;
import ToggleGame.frontend.ToggleGameInteraction;
import static ToggleGame.backend.GameHelper.binaryToString;
import static ToggleGame.backend.GameHelper.stringToBinary;

/**
 * Class to handle all game board operations.
 *
 * @author Joanthon Meney
 * @version 1.0 03/09/23
 */
public class ToggleGameEngine implements ToggleGameInteraction {
    /**
     * Holds the graph of all game states connected by making one move to get to an adjacent vertex.
     */
    private static Graph graph;

    /**
     * Returns a 3x3 board of all white squares.
     * @return the string "111111111", and all white board
     */
    @Override
    public String initializeGame() {
        return "111111111";
    }

    /**
     * Returns the updated board string after a button has been clicked.
     *
     * @param currentBoard the board string before a move is made
     * @param button the button that was clicked
     * @return the board string after a move is made
     * @throws IllegalArgumentException if button given is outside range [0,8]
     */
    @Override
    public String buttonClicked(String currentBoard, int button) {
        if (button < 0 || button > 8) {
            throw new IllegalArgumentException();
        }

        // convert to binary
        // flip color of button pressed first
        int currentBoardBinary = stringToBinary(currentBoard);
        currentBoardBinary ^= (1 << (8 - button));

        // flip the color of the squares above, below, left, and right of the button clicked
        // if they exist
        int boardSize = 3;
        // NORTH
        if (button - boardSize >= 0) {
            currentBoardBinary ^= (1 << (8 - button + boardSize));
        }

        // SOUTH
        if (button + boardSize <= 8) {
            currentBoardBinary ^= (1 << (8 - button - boardSize));
        }

        // EAST
        if ((button + 1) % boardSize != 0) {
            currentBoardBinary ^= (1 << (8 - button - 1));
        }

        // WEST
        if (button % boardSize != 0) {
            currentBoardBinary ^= (1 << (8 - button + 1));
        }

        return binaryToString(currentBoardBinary);
    }

    /**
     * Returns the array of moves required to go from one game state to another.
     *
     * @param current the current game state
     * @param target the target game state
     * @return the array of moves required to go from the current state to the target state
     */
    @Override
    public int[] movesToSolve(String current, String target) {
        // if we've reached the target no moves need to be made
        if (current.equals(target)) {
            return new int[]{};
        }

        // only builds the graph on first call since graph is static
        if (graph == null) {
            graph = buildGraph();
        }

        // breadth first search from target game state
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, stringToBinary(target));

        // get the shortest path from the current game state to the target game state
        // iterable is only comprised of game states not moves, need to convert
        Iterable<Integer> path = breadthFirstPaths.pathTo(stringToBinary(current));

        // convert iterable to array list
        ArrayList<Integer> arrayPath = new ArrayList<>();
        for (Integer board : path) {
            arrayPath.add(board);
        }

        // determine buttons which need to be clicked to go from game state to game state
        // up to the target
        int[] movePath = new int[arrayPath.size()-1];
        for (int i = 0; i+1 < arrayPath.size(); i++) {
            String initial = binaryToString(arrayPath.get(i));
            String next = binaryToString(arrayPath.get(i+1));
            for (int button = 0; button < 9; button++) {
                // break out of button finding once we find
                // the button that takes us from the current state to the next
                if (buttonClicked(initial, button).equals(next)) {
                    movePath[i] = button;
                    break;
                }
            }
        }

        return movePath;
    }

    /**
     * Returns the minimum number of moves to go from one game state to another.
     *
     * @param current the current game state
     * @param target the target game state
     * @return the minimum number of moves to go from the current state to the target state
     */
    @Override
    public int minNumberOfMoves(String current, String target) {
        // simply find the moves to get to the target and count them
        return movesToSolve(current, target).length;
    }

    /**
     * Builds a graph of all game states.
     *
     * @return the graph of all game states connected by making one move to get to an adjacent vertex
     */
    public Graph buildGraph() {
        // 512 possible game states 2**9
        Graph graph = new Graph(512);

        // for every game state generate all the game states one move away
        // and connect them with an edge
        for (int vertex = 0; vertex < 512; vertex++) {
            String board = binaryToString(vertex);
            for (int button = 0; button < 9; button++) {
                String newBoard = buttonClicked(board, button);
                graph.addEdge(stringToBinary(board), stringToBinary(newBoard));
            }
        }

        return graph;
    }
}
