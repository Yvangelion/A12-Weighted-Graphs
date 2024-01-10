import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// K System

// representation of each edge in graph
class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class A12p2 {

   
    private static int find(int[] parent, int i) { // find starting point
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    private static void union(int[] parent, int x, int y) { // find union of values
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        parent[rootX] = rootY;
    }

    public static void kmst(int[][] graph, char[] vertices) { // 
        int numVertices = graph.length;
        List<Edge> edges = new ArrayList<>();

        
        for (int i = 0; i < numVertices; i++) { // save the edges from the graph
            for (int j = i + 1; j < numVertices; j++) {
                if (graph[i][j] != 0) {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }

        edges.sort(Comparator.comparingInt(e -> e.weight)); // sort based on weight

        int[] parent = new int[numVertices];
        Arrays.fill(parent, -1);

        System.out.println("MST:");

        int distance = 0; // distance

        for (Edge edge : edges) { // if it does not form a cyle add to mst
            int rootSrc = find(parent, edge.src);
            int rootDest = find(parent, edge.dest);

            if (rootSrc != rootDest) { // print values
                System.out.println(vertices[edge.src] + " - " + vertices[edge.dest] + " : " + edge.weight);
                distance += edge.weight;
                union(parent, rootSrc, rootDest);
            }
        }

        System.out.println("Traveled distance: " + distance);
    }

    public static void main(String[] args) {
        char[] vertices = {'A', 'C', 'F', 'D', 'G', 'E', 'H', 'B'};
        int[][] graph = {
            
        //   A, C, F, D, G ,E , H, B data
            {0, 4, 7, 0, 0, 0, 0, 0}, //A
            {4, 0, 2, 3, 9, 0, 0, 0}, //C
            {7, 2, 0, 8, 0, 0, 0, 0}, //F
            {0, 3, 0, 0, 7, 3, 0, 0}, //D
            {0, 0, 8, 7, 0, 2, 3, 0}, //G
            {0, 0, 0, 3, 2, 0, 7, 9}, //E
            {0, 0, 0, 0, 3, 7, 0, 3}, //H
            {0, 0, 0, 0, 0, 9, 3, 0}  //B
        };

        
        kmst(graph, vertices);
    }
}
