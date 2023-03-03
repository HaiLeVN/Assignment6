
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thanh Hai
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        graph.load("Data.txt");
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Convert1() ");
            System.out.println("2. Convert2() ");
            System.out.println("3. BFS(Vertex X) ");
            System.out.println("4. DFS(Vertex x) ");
            System.out.println("5. Findpath(Vertex x, Vertex y) ");
            System.out.println("6. MST1(Vertex x)");
            System.out.println("7. MST2()");
            System.out.println("8. Euler(Vertex x)");
            System.out.println("Others - Quit");
            System.out.print("Choice:");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice) {
                case 1:
                    //Convert 1
                    System.err.print("Not supported yet. Please call it in main\n");
                    break;
                case 2:
                    //Convert 2
                    System.err.print("Not supported yet. Please call it in main\n");
                    break;
                case 3:
                    //BFS(vertex X)
                    System.out.print("Start from vertex: ");
                    String vertex = sc.nextLine();
                    System.err.print("Not supported yet. Please call it in main\n");
                    break;
                case 4:
                    //DFS(vertex X)
                    System.out.print("Start from vertex: ");
                    vertex = sc.nextLine();
                    System.err.print("Not supported yet. Please call it in main\n");
                    break;
                case 5:
                    //Dijkstra algorithms
                    System.out.print("Start from vertex: ");
                    String startV = sc.nextLine();
                    System.out.print("To vertex: ");
                    String endY = sc.nextLine();
                    System.err.print("Not supported yet. Please call it in main\n");
                    break;
                case 6:
                    //Prim-Janik algorithms
                    System.out.print("Start from vertex: ");
                    vertex = sc.nextLine();
                    System.err.print("Not supported yet. Please call it in main\n");
                    break;
                case 7:
                    //Kruskal algorithms
                    System.err.print("Not supported yet. Please call it in main\n");
                    break;
                case 8:
                    //Euler cycle
                    System.out.print("Start from vertex: ");
                    vertex = sc.nextLine();
                    System.err.print("Not supported yet. Please call it in main\n");
                    break;
            }
        } while (choice > 1 && choice < 9);
        System.out.println("Good bye!");
    }
}
