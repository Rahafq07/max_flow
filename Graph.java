

public class Graph {
    
    // global variables declaration
    
    public int[][] adj; //adjacency matrix
    public int source ; // source vertex
    public int sink ; // sink vertex
 
 /**
 * <h1> Graph class  </h1>
 * 
 * This is the Graph class which is supporting class for max flow class .
 * 
 * @author Rahaf , sarah , Somayah 
 * @version 8.2
 * @since 21-12-2021
 */
    public Graph() {
        
        //adjacency matrix
        adj = new int[][] { {0, 2, 7, 0, 0, 0},
                            {0, 0, 0, 3, 4, 0},
                            {0, 0, 0, 4, 2, 0},
                            {0, 0, 0, 0, 0, 1},
                            {0, 0, 0, 0, 0, 5},
                            {0, 0, 0, 0, 0, 0}
                          };
        this.source = 0 ; // source vertex 1
        this.sink = adj.length-1 ; // sink vertex 6
    }
    
    //getter
    
    public int[][] getAdj() {
        return adj;
    }
 
    public int getSource() {
        return source;
    }

    public int getSink() {
        return sink;
    }
 
}


