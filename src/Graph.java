
import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Thanh Hai
 */
public class Graph {

    int V; // number of vertices
    int E; // number of edges
    char[] vertex;
    List<List<Integer>> adjList; // adjacency list
    int[][] adjMatrix; // adjacency matrix
    int[][] incMatrix; // incidence matrix

    // constructor with no arguments
    public Graph() {
        V = 0;
        E = 0;
        vertex = new char[V];
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

    public void DFS(int startVertex) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        visited[startVertex] = true;
        stack.push(startVertex);

        while (!stack.empty()) {
            int currentVertex = stack.pop();
            System.out.print(currentVertex + " ");
            List<Integer> neighbors = adjList.get(currentVertex);
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
        System.out.println();
    }

    public List<Integer> dijkstra(int s, int d) {
        // initialize distances to all vertices to infinity
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        // initialize priority queue for vertices to visit
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(V, new Comparator<Integer>() {
            public int compare(Integer u, Integer v) {
                return dist[u] - dist[v];
            }
        });
        pq.offer(s);

        // initialize array to track visited vertices
        boolean[] visited = new boolean[V];

        // initialize array to track shortest path
        int[] prev = new int[V];
        Arrays.fill(prev, -1);

        // iterate until destination vertex is reached or queue is empty
        while (!pq.isEmpty()) {
            int u = pq.poll();
            visited[u] = true;
            if (u == d) {
                break;
            }
            for (int v : adjList.get(u)) {
                if (!visited[v]) {
                    int alt = dist[u] + adjMatrix[u][v];
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        prev[v] = u;
                        pq.offer(v);
                    }
                }
            }
        }

        // construct shortest path as list of vertices
        List<Integer> path = new ArrayList<Integer>();
        for (int v = d; v != -1; v = prev[v]) {
            path.add(v);
        }
        Collections.reverse(path);

        return path;
    }

    public List<Integer> eulerCycle(int start) {
        List<Integer> cycle = new ArrayList<>();
        // Check if the graph has edges
        if (E == 0) {
            return cycle;
        }
        // Check if the start vertex is valid
        if (start < 0 || start >= V) {
            throw new IllegalArgumentException("Invalid start vertex");
        }
        // Create a copy of the adjacency list
        List<List<Integer>> adjListCopy = new ArrayList<>(adjList);
        // Initialize a stack to keep track of the vertices
        Deque<Integer> stack = new ArrayDeque<>();
        // Add the start vertex to the stack
        stack.push(start);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            // Check if the current vertex has any neighbors
            if (!adjListCopy.get(v).isEmpty()) {
                // Push the first neighbor onto the stack
                int w = adjListCopy.get(v).remove(0);
                // Remove the edge between v and w
                adjListCopy.get(w).remove(Integer.valueOf(v));
                // Push w onto the stack
                stack.push(w);
            } else {
                // The current vertex has no neighbors, so pop it off the stack and add it to the cycle
                int u = stack.pop();
                cycle.add(u);
            }
        }
        // Reverse the cycle to get the correct order of vertices
        Collections.reverse(cycle);
        return cycle;
    }

}
