package com.dixit.soham.aoc.day10.pipes;

import com.dixit.soham.aoc.common.Solver;
import org.apache.commons.lang3.tuple.Pair;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PipeMazeSolver extends Solver {

    // NESW encoding for the pipes
    private static final Map<Character, Integer> ENCODING = Map.of(
            '|', 10,
            '-', 5,
            'L', 12,
            'J', 9,
            '7', 3,
            'F', 6,
            '.', 0
    );

    private Graph<Pair<Integer, Integer>, DefaultEdge> graph;
    private Pair<Integer, Integer> startVertex;
    private int rows;
    private int cols;

    private List<Pair<Integer, Integer>> cycle;
    private List<String> lines;
    private Long stepsToFurthestPath;

    public PipeMazeSolver(InputStream inputStream) {
        super(inputStream);
        this.cycle = new ArrayList<>();
    }

    @Override
    public void solve() throws IOException {
        String line = reader.readLine();
        List<String> lines = new ArrayList<>();
        int l = line.length();

        lines.add(".".repeat(l + 2));
        while (line != null) {
            lines.add("." + line + ".");
            // read next line
            line = reader.readLine();
        }
        lines.add(".".repeat(l + 2));

        this.lines = lines;
        this.rows = lines.size();
        this.cols = lines.get(0).length();
        this.graph = buildGraphFrom(lines);
        this.stepsToFurthestPath = cycleLength(this.graph) / 2;
    }

    public Long getStepsToFurthestPoint() {
        printSearchPath();
        printCycle();
        return this.stepsToFurthestPath;
    }

    public double getInteriorPoints() {
        printCycle();
        double area = shoelace_formula(new ArrayList<>(cycle));
        System.out.printf("%nArea is %s%n", area);
        // use Picks theorem to get number of interior points
        // A = i  + (b/2) - 1
        // i = A - (b/2) + 1
        int b = cycle.size();
        return (area - ((double) b / 2) + 1);
    }

    private static double shoelace_formula(List<Pair<Integer, Integer>> points) {
        double area = 0.0;

        int j;
        for (int i = 0; i < points.size(); i++) {
            j = (i + 1) % points.size();
            area += (points.get(i).getLeft() * points.get(j).getRight())
                    -  (points.get(i).getRight() * points.get(j).getLeft());
        }

        return Math.abs(area / 2.0);
    }

    private Graph<Pair<Integer, Integer>, DefaultEdge> buildGraphFrom(List<String> lines) {

        Graph<Pair<Integer, Integer>, DefaultEdge> g = new DefaultUndirectedGraph<>(DefaultEdge.class);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g.addVertex(Pair.of(i, j));
            }
        }

        for (int i = 1; i < rows - 1; i++) {
            String row = lines.get(i);
            for (int j = 1; j < row.length() - 1; j++) {
                char pipeType = row.charAt(j);
                switch (pipeType) {
                    case '|': {
                        char charAtNorth = charAt(i - 1, j);
                        if (canConnect('S', charAtNorth)) g.addEdge(Pair.of(i - 1, j), Pair.of(i, j));
                        char charAtSouth = charAt(i + 1, j);
                        if (canConnect('N', charAtSouth)) g.addEdge(Pair.of(i, j), Pair.of(i + 1, j));
                        break;
                    }
                    case '-': {
                        char charAtWest = charAt(i, j - 1);
                        if (canConnect('E', charAtWest)) g.addEdge(Pair.of(i, j - 1), Pair.of(i, j));
                        char charAtEast = charAt(i, j + 1);
                        if (canConnect('W', charAtEast)) g.addEdge(Pair.of(i, j), Pair.of(i, j + 1));
                        break;
                    }
                    case 'L': {
                        char charAtNorth = charAt(i - 1, j);
                        if (canConnect('S', charAtNorth)) g.addEdge(Pair.of(i - 1, j), Pair.of(i, j));
                        char charAtEast = charAt(i, j + 1);
                        if (canConnect('W', charAtEast)) g.addEdge(Pair.of(i, j), Pair.of(i, j + 1));
                        break;
                    }
                    case 'J': {
                        char charAtNorth = charAt(i - 1, j);
                        if (canConnect('S', charAtNorth)) g.addEdge(Pair.of(i - 1, j), Pair.of(i, j));
                        char charAtWest = charAt(i, j - 1);
                        if (canConnect('E', charAtWest)) g.addEdge(Pair.of(i, j - 1), Pair.of(i, j));
                        break;
                    }
                    case '7': {
                        char charAtSouth = charAt(i + 1, j);
                        if (canConnect('N', charAtSouth)) g.addEdge(Pair.of(i, j), Pair.of(i + 1, j));
                        char charAtWest = charAt(i, j - 1);
                        if (canConnect('E', charAtWest)) g.addEdge(Pair.of(i, j - 1), Pair.of(i, j));
                        break;
                    }
                    case 'F': {
                        char charAtSouth = charAt(i + 1, j);
                        if (canConnect('N', charAtSouth)) g.addEdge(Pair.of(i, j), Pair.of(i + 1, j));
                        char charAtEast = charAt(i, j + 1);
                        if (canConnect('W', charAtEast)) g.addEdge(Pair.of(i, j), Pair.of(i, j + 1));
                        break;
                    }
                    case '.': {
                        break;
                    }
                    case 'S': {
                        this.startVertex = Pair.of(i, j);
                        break;
                    }
                    default: {
                        throw new RuntimeException("Unrecognized character : " + pipeType);
                    }
                }
            }
        }

        return g;
    }

    private char charAt(int i, int j) {
        return this.lines.get(i).charAt(j);
    }

    private boolean canConnect(char direction, char pipe) {
        if (pipe == 'S') return true;
        return switch (direction) {
            case 'N' -> (ENCODING.get(pipe) & 8) != 0;
            case 'E' -> (ENCODING.get(pipe) & 4) != 0;
            case 'S' -> (ENCODING.get(pipe) & 2) != 0;
            case 'W' -> (ENCODING.get(pipe) & 1) != 0;
            default -> false;
        };
    }
    // Returns true if the graph

    // contains a cycle, else false.

    Long cycleLength(Graph<Pair<Integer, Integer>, DefaultEdge> graph) {

        // Mark all the vertices as
        // not visited and not part of
        // recursion stack
        Boolean[][] visited = new Boolean[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                visited[i][j] = false;

        return cycleLengthHelper(
                graph,
                startVertex,
                visited,
                Pair.of(-1, -1),
                0L
        );
    }

    Long cycleLengthHelper(Graph<Pair<Integer, Integer>, DefaultEdge> graph,
                           Pair<Integer, Integer> v,
                           Boolean[][] visited,
                           Pair<Integer, Integer> parent,
                           long length
    ) {

        //System.out.printf("Visiting %s Length is %s%n ", v, length);
        // Mark the current node as visited
        visited[v.getLeft()][v.getRight()] = true;
        cycle.add(v);

        List<Pair<Integer, Integer>> neighbours = Graphs.neighborListOf(graph, v);

        /*System.err.printf(
                "%s - Current - %s [%s], Neighbours - %s%n",
                length, v,
                lines.get(v.getLeft()).charAt(v.getRight()),
                neighbours
        );*/


        for (Pair<Integer, Integer> neighbour : neighbours) {

            // If an adjacent is not
            // visited, then recur for that
            // adjacent
            if (!visited[neighbour.getLeft()][neighbour.getRight()]) {

//                System.err.printf("Visiting %s next.%n%n", neighbour);

                Long l = cycleLengthHelper(graph, neighbour, visited, v, length + 1);
                if (l != 0)
                    return l;
            }

            // If an adjacent is visited
            // and not parent of current
            // vertex, then there is a cycle.
            else if (!neighbour.equals(parent))
                return length + 1;
        }
        return 0L;
    }

    private void printCycle() {
        System.out.println();

        for (int i = 0; i < lines.size(); i++) {
            System.out.print("[");
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (cycle.contains(Pair.of(i, j))) System.out.printf("%s\t", line.charAt(j));
                else System.out.print("\t");
            }
            System.out.println("]");
        }
    }

    private void printSearchPath() {
        System.out.println();
        int[][] path = new int[rows][cols];

        for (int[] p : path) {
            Arrays.fill(p, -1);
        }

        int l = 0;
        for (Pair<Integer, Integer> v : this.cycle) {
            path[v.getLeft()][v.getRight()] = l++;
        }
        for (int[] row : path) {
            System.out.print("[");
            for (int i : row) {
                if (i != -1) System.out.printf("%s\t", i);
                else System.out.print("\t");
            }
            System.out.println("]");
        }
    }
}
