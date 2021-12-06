
import java.util.LinkedList;

/**
 * <h1> Compute The maximum flow and the minimum cut Using Edmonds-Karp Algorithm </h1>
 * 
 * the program will find the maximum flow and the minimum cut of a given network (source: vertex 1, sink: vertex 6) using Edmonds-Karp Algorithm .
 * 
 * @author Rahaf , sarah , Somayah 
 * @version 8.2
 * @since 27-11-2021
 */

public class max_flow {

    // global variables declaration
    
    static int vertices = 6; //Number of vertices in network

    /**
     * This is the main method which create an object of type Graph, 
     * store the source and the Sink, print the starting sentence, 
     * and send Graph, source and the Sink to EdmondsKarp method. 
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Graph graph = new Graph(); // object from Graph type 
        int source = graph.getSource(); //Determine the source
        int sink = graph.getSink(); //Determine the sink
        
        //starting sentence
        System.out.println("------------------- WELCOME TO Edmonds Karp ALGORITHM -------------------\n");

        //call EdmondsKarp
        EdmondsKarp(graph, source, sink);

    }

    /**
     * This is the EdmondsKarp method which find the maximum flow of the network using BFS, and the corresponding minimum cut.
     * 
     * @param graph graph object
     * @param source network source
     * @param sink network sink
     */
    public static void EdmondsKarp(Graph graph, int source, int sink) {

        
        System.out.println("--------------------------------------");
        System.out.println("             Maximum flow             ");
        System.out.println("--------------------------------------");
        
        System.out.println("\n> Augiminting paths :\n");
        
        int i, j;

        // make a remaining Graph array whith the same capacities of the original graph 
        int remGraph[][] = new int[vertices][vertices];
        
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                // Storing all edges from the original Graph to the remaining Graph (If rGraph [i] [j] is 0, then there is Not an edge)
                remGraph[i][j] = graph.adj[i][j];
            }
        }
        
        // This array is filled by BFS and to store path
        int parent[] = new int[vertices];
        // variable to store max flow
        int max_flow = 0;

        //while we reach to the sink in BFS starting from source
        while (bfs(remGraph, source, sink, parent)) {
            
            String path = ""; //string variable that stores the path
            int pathFlow = Integer.MAX_VALUE; // Storing an infinite number in path flow
            
            for (j = sink; j != source; j = parent[j]) {
                
                String direction = " ← "; // String variable that specify the direction of the path
                i = parent[j];
                pathFlow = Math.min(pathFlow, remGraph[i][j]); //Returns the smaller of two int values 
                
                if (graph.adj[i][j] != 0) {
                    direction = " → "; // String variable that specify the direction of the path
                }
                path = direction + (j + 1) + path;
            }
            path = (j + 1) + path;
            
            System.out.printf("%-17s \n", path);
            System.out.print("flow : "+pathFlow);
            
            for (j = sink; j != source; j = parent[j]) {
                
                i = parent[j]; 
                remGraph[i][j] -= pathFlow; //decrease edge by flow path
                remGraph[j][i] += pathFlow; //Increase edge by flow path
                
            }
            
            // Add path flow to overall flow
            max_flow += pathFlow;
            System.out.println(" , Updated flow : " + max_flow + "\n");

        }
        
        // print max-flow
            System.out.println("\n>> The maximum flow is : "+max_flow);
            
        //-----------------------------------------------------------------
        
            System.out.println("\n\n--------------------------------------");
            System.out.println("             Minimum cut              ");
            System.out.println("--------------------------------------");
            
            // print min-cut edges
            System.out.println("\n> Edges included in the min-cut :");
            
            // boolean array to check if visited
            boolean[] isVisited = new boolean[graph.adj.length];
            
            //find vertices reachable from source and mark it visited
            dfs(remGraph, source, isVisited);
            
            // Print all edges that are from a reachable vertex to non-reachable vertex in the original graph
            int total_cut=0;
            
            for (i = 0; i < graph.adj.length; i++) {
                for (j = 0; j < graph.adj.length; j++) {
                    
                    if (graph.adj[i][j] > 0 && isVisited[i] && !isVisited[j]) {
                        
                        System.out.print("\nEdge: "+(i+1) + "-" + (j+1));
                        System.out.println(" , capacity = "+graph.adj[i][j]);
                        total_cut+=graph.adj[i][j];
                        System.out.println("Updated min-cut capicity: "+total_cut);
                        
                    }
                }
            }
            
            System.out.println("\n>> The total min-cut capacity is :"+total_cut+"\n");
    }

    /**
     * This is the bfs method which is supporting method to find the max-flow
     * 
     * @param remGraph remaining Graph
     * @param source network source
     * @param sink network sink
     * @param parent array is filled by BFS and to store path
     * @return true if we reached sink in BFS starting from source, otherwise false 
     */
    private static boolean bfs(int[][] remGraph, int source, int sink, int[] parent) {
        
        //Create visited array and mark all vertices as not visited
        boolean visited[] = new boolean[vertices];
        
            for(int i=0; i<vertices; ++i)
                visited[i]=false;
            
            //Create a queue
            LinkedList<Integer> queue = new LinkedList<>();
            
            //enqueue source vertex and mark source vertex as visited
            queue.add(source);
            visited[source] = true;
            parent[source]=-1;
            
            // Standard BFS Loop
            while (!queue.isEmpty())
            {
                int u = queue.poll();
                for (int v=0; v<vertices; v++)
                {
                    if (visited[v]==false && remGraph[u][v] > 0)
                    {
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }
        
        //If we reached sink in BFS starting from source, then return true, else return false  
        return (visited[sink] == true);
    }

    /**
     * This is the dfs method which is supporting method to find the min-cut
     * @param remGraph remaining Graph
     * @param source network source
     * @param visited boolean array to check if visited
     */
    private static void dfs(int[][] remGraph, int source, boolean[] visited) {
        
        //marks visited[source] as true if i is reachable from source
        visited[source]=true;
            for (int i = 0; i < remGraph.length; i++) {
                if(remGraph[source][i]>0&&!visited[i])
                    dfs(remGraph,i,visited);
            }

    }

}
