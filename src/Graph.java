import java.io.*;
import java.util.*;

public class Graph {
    int V; // number of vertices
    int E; // number of edges
    List<List<Integer>> adjList; // adjacency list
    int[][] adjMatrix; // adjacency matrix
    int[][] incMatrix; // incidence matrix

    // constructor with no arguments
    public Graph() {
        V = 0;
        E = 0;
        adjList = new ArrayList<List<Integer>>();
        adjMatrix = null;
        incMatrix = null;
    }

    // constructor that takes number of vertices as argument
    public Graph(int V) {
        this.V = V;
        E = 0;
        adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        adjMatrix = new int[V][V];
        incMatrix = null;
    }

    // method to load graph data from file
    public void load(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String[] vertices = reader.readLine().split("\\s+");
        V = vertices.length;
        adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        adjMatrix = new int[V][V];
        for (int i = 0; i < V; i++) {
            String[] edges = reader.readLine().split("\\s+");
            for (int j = 0; j < V; j++) {
                int weight = Integer.parseInt(edges[j]);
                if (weight > 0) {
                    E++;
                    adjList.get(i).add(j);
                    adjMatrix[i][j] = weight;
                }
            }
        }
        incMatrix = new int[V][E];
        for (int i = 0, k = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adjMatrix[i][j] > 0) {
                    incMatrix[i][k] = adjMatrix[i][j];
                    incMatrix[j][k] = -adjMatrix[i][j];
                    k++;
                }
            }
        }
        System.out.println("Automatically loaded Data.txt.");
        reader.close();
    }
}