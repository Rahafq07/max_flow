
public class WGraph {

    // global variables declaration
    
    int vertex; // number of vertex
    int[][] matrix; //adjacency matrix
    public int source; // source vertex
    public int sink; // sink vertex

    /**
     * <h1> Graph class  </h1>
     *
     * This is the Graph class which is supporting class for max flow class .
     *
     * @author Rahaf , sarah , Somayah
     * @version 8.2
     * @since 27-11-2021
     */
    
    // constructor
    public WGraph(int vertex) {
        this.vertex = vertex;
        matrix = new int[vertex][vertex];

        //add Edge to adjacency matrix
        addEdge(0, 1, 2);
        addEdge(0, 2, 7);
        addEdge(1, 3, 3);
        addEdge(1, 4, 4);
        addEdge(2, 3, 4);
        addEdge(2, 4, 2);
        addEdge(3, 5, 1);
        addEdge(4, 5, 5);

        this.source = 0; // source vertex 1
        this.sink = matrix.length - 1; // sink vertex 6

    }

    // Add edges
    public void addEdge(int source, int sink, int weight) {
        matrix[source][sink] = weight;
    }

    // Remove edges
    public void removeEdge(int source, int sink) {
        matrix[source][sink] = 0;
    }

    // Print the matrix
    public void printGraph() {
        System.out.println("Adjacency Matrix : ");
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    //getter
    public int getSource() {
        return source;
    }

    public int getSink() {
        return sink;
    }
}

