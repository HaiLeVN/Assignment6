
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

        int choice;
        Graph graph = new Graph();
        graph.load("Data.txt"); //tu dong load Data.txt
        Scanner sc = new Scanner(System.in);
        do {

            System.out.println("0. Load specific files");
            System.out.println("1. Convert1() ");
            System.out.println("2. Convert2() ");
            System.out.println("3. BFS(Vertex X) ");
            System.out.println("4. DFS(Vertex x) ");
            System.out.println("5. Findpath(Vertex x, Vertex y) ");
            System.out.println("6. MST1(Vertex x)");
            System.out.println("7. MST2()");
            System.out.println("8. Euler(Vertex x)");
            System.out.println("Others - Quit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine());
            try {
                switch (choice) {
                    case 0:
                        System.out.print("Files name (.txt): ");
                        String file = sc.nextLine();
                        graph.load(file);
                        break;
                    case 1:
                        //Convert 1
                        graph.convert1();
                        System.out.print("Press Enter to continue.");
                        sc.nextLine();
                        break;
                    case 2:
                        //Convert 2
                        graph.convert2();
                        System.out.print("Press Enter to continue.");
                        sc.nextLine();
                        break;
                    case 3:
                        //BFS(vertex X)
                        System.out.println("");
                        System.out.print("BFS from the vertex:");
                        int v4 = Integer.parseInt(sc.nextLine());
                        graph.BFS(v4);
                        System.out.print("Press Enter to continue.");
                        sc.nextLine();
                        break;
                    case 4:
                        //DFS(vertex X)
                        System.out.print("Start from vertex: ");
                        int startVertex = Integer.parseInt(sc.nextLine());
                        graph.DFS(startVertex);
                        System.out.print("Press Enter to continue.");
                        sc.nextLine();
                        break;
                    case 5:
                        //Dijkstra algorithms
                        System.out.print("Start from vertex: ");
                        int startV = Integer.parseInt(sc.nextLine());
                        System.out.print("To vertex: ");
                        int endV = Integer.parseInt(sc.nextLine());
                        graph.dijkstra(startV, endV);
                        System.out.print("Press Enter to continue.");
                        sc.nextLine();
                        break;
                    case 6:
                        //Prim-Janik algorithms
                        System.out.print("Start from vertex: ");
                        startVertex = Integer.parseInt(sc.nextLine());
                        graph.MST1(startVertex);
                        System.out.print("Press Enter to continue.");
                        sc.nextLine();
                        break;
                    case 7:
                        //Kruskal algorithms
                        graph.MST2();
                        System.out.print("Press Enter to continue.");
                        sc.nextLine();
                        break;
                    case 8:
                        //Euler cycle
                        System.out.print("Start from vertex: ");
                        int start = Integer.parseInt(sc.nextLine());
                        graph.eulerCycle(start);
                        System.out.print("Press Enter to continue.");
                        sc.nextLine();
                        break;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid input, please try again.");
            }
        } while (choice > -1 && choice < 9);
        System.out.println("Good bye!");
    }
}
