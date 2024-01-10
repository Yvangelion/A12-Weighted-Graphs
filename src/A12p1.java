import java.util.Arrays;

// P System

public class A12p1 {

    private static final int INFINITY = Integer.MAX_VALUE;

    public static void prim_al(int[][] graph, char[] vertices) {
        int numVertices = graph.length;
        int[] parent = new int[numVertices];
        int[] key = new int[numVertices];
        boolean[] mstSet = new boolean[numVertices];

        // Initialize key values to infinity and mstSet to false
        Arrays.fill(key, INFINITY);
        Arrays.fill(mstSet, false);

        key[0] = 0; // start on first index
        parent[0] = -1;
        
        for (int count = 0; count < numVertices - 1; count++) { // visit all values
            int u = minKey(key, mstSet); // view method
            mstSet[u] = true;

            for (int v = 0; v < numVertices; v++) { // update the values of vertices as we traverse
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        printvalues(parent, graph, vertices); 
    }

    private static int minKey(int[] key, boolean[] mstSet) { 
        int min = INFINITY;
        int minIndex = -1;

        for (int v = 0; v < key.length; v++) { // if value is smallest keep it and use it
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private static void printvalues(int[] parent, int[][] graph, char[] vertices) {
        int distance = 0;
        System.out.println("edge \tweight");
        for (int i = 1; i < graph.length; i++) { // loop to print distance and edges as it travels through graph
            System.out.println(vertices[parent[i]] + " - " + vertices[i] + " : " + graph[i][parent[i]]);
            distance += graph[i][parent[i]];
        }
        System.out.println("distance: " + distance);
    }

    public static void main(String[] args) {
        char[] vertices = {'A', 'C', 'F', 'D', 'G', 'E', 'H', 'B'};
        int[][] graph = {
        //   A, C, F, D, G ,E , H, B            data
            {0, 4, 7, 0, 0, 0, 0, 0}, //A
            {4, 0, 2, 3, 9, 0, 0, 0}, //C
            {7, 2, 0, 8, 0, 0, 0, 0}, //F
            {0, 3, 0, 0, 7, 3, 0, 0}, //D
            {0, 0, 8, 7, 0, 2, 3, 0}, //G
            {0, 0, 0, 3, 2, 0, 7, 9}, //E
            {0, 0, 0, 0, 3, 7, 0, 3}, //H
            {0, 0, 0, 0, 0, 9, 3, 0}  //B
        };

        prim_al(graph, vertices);   
    }
}
