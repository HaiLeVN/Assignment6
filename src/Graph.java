
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

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
        vertex = new char[V];
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
        vertex = new char[V];
        for (int i = 0; i < V; i++) {
            vertex[i] = vertices[i].charAt(0);
        }
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
        System.out.println();
        reader.close();
    }
    // from adjacency matrix to incident matrix and print 

    public void convert1() {
        incMatrix = new int[V][E];
        int edgeCount = 0;
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (adjMatrix[i][j] > 0) {
                    incMatrix[i][edgeCount] = 1;
                    incMatrix[j][edgeCount] = 1;
                    edgeCount++;
                }
            }
        }
        for (int i = 0; i < V; i++) {
            for(int j = 0; j < V; j ++) {
              System.out.print(incMatrix[i][j] + " "); 
            }
            System.out.println();
        }
    }



    // from adjacency matrix to adjacency list and print
    public void convert2() {
        adjList = new ArrayList<List<Integer>>();
        for (int i = 0; i < V; i++) {
            List<Integer> neighbors = new ArrayList<Integer>();
            for (int j = 0; j < V; j++) {
                if (adjMatrix[i][j] != 0) {
                    neighbors.add(j);
                }
            }
            adjList.add(neighbors);
        }
        for (int i = 0; i < V; i++) {
            System.out.print(vertex[i] + ": ");
            for (int neighbor : adjList.get(i)) {
                System.out.print(vertex[neighbor] + " ");
            }
            System.out.println();
        }
    }

    void visit(int p) {
        System.out.print(vertex[p] + " ");
    }

    void BFS(int x) {
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        int p = x;
        MyQueue my = new MyQueue();
        my.EnQueue(p);
        while (!my.isEmpty()) {
            int q = my.DeQueue();
            visited[q] = true;
            for (int i = 0; i < V; i++) {
                if (!visited[i] && adjMatrix[i][q] != 0) {
                    visited[i] = true;
                    my.EnQueue(i);
                }
            }
            visit(q);
        }
    }

    public void DFS(int startVertex) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        visited[startVertex] = true;
        stack.push(startVertex);

        while (!stack.empty()) {
            int currentVertex = stack.pop();
            System.out.print(vertex[currentVertex] + " ");
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

    public void dijkstra(int s, int d) {
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
        int current = d;
        while (current != -1) {
            path.add(current);
            current = prev[current];
        }
        Collections.reverse(path);

        // print path with distance
        StringBuilder sb = new StringBuilder();
        sb.append(vertex[path.get(0)]).append("(").append(0).append(")");
        for (int i = 1; i < path.size(); i++) {
            int from = path.get(i - 1);
            int to = path.get(i);
            int distance = adjMatrix[from][to];
            sb.append(" -> ");
            sb.append(vertex[to]).append("(").append(distance).append(")");
        }
        System.out.println(sb.toString());
    }

    public void eulerCycle(int start) {
        // Check if the graph has edges
        if (E == 0) {
            return;
        }
        // Check if the start vertex is valid
        if (start < 0 || start >= V) {
            System.err.println("Invalid start vertex");
            return;
        }
        // Find the degree of each vertex
        int[] degree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adjList.get(u)) {
                degree[u]++;
            }
        }
        // Check if there are two vertices with odd degree
        int oddCount = 0;
        int oddVertex = -1;
        for (int u = 0; u < V; u++) {
            if (degree[u] % 2 == 1) {
                oddCount++;
                oddVertex = u;
            }
        }
        if (oddCount > 2) {
            System.err.println("Graph is not Eulerian");
            return;
        }
        // If there is one vertex with odd degree, use it as start vertex
        if (oddCount == 1) {
            start = oddVertex;
        }
        // Create a copy of the adjacency list to remove edges as they are used
        List<List<Integer>> adjListCopy = new ArrayList<>();
        for (int u = 0; u < V; u++) {
            adjListCopy.add(new ArrayList<>(adjList.get(u)));
        }
        // Create a stack to keep track of the current path
        Stack<Integer> stack = new Stack<>();
        // Create a list to keep track of the Eulerian path
        List<Integer> path = new ArrayList<>();
        stack.push(start);
        while (!stack.empty()) {
            int u = stack.peek();
            if (adjListCopy.get(u).size() > 0) {
                int v = adjListCopy.get(u).remove(0);
                stack.push(v);
                adjListCopy.get(v).remove(Integer.valueOf(u));
            } else {
                stack.pop();
                path.add(u);
            }
        }
        Collections.reverse(path);
        // Print the path with vertex labels
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(vertex[path.get(i)]);
            if (i != path.size() - 1) {
                sb.append(" -> ");
            }
        }
        System.out.println(sb.toString());
    }
}
