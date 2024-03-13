import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dijkstra {
    public static void main(String[] args) {

        String fileName = "D:\\SCHOOL\\2024\\Winter Quarter\\CSCD 320\\Programming_Assignments\\prog2\\CSCD320-Prog2\\prog5\\src\\test.txt";
        int src = 0;

        /*if (args.length == 2) {
            fileName = args[0];
            src = Integer.parseInt(args[1]);
        } else {
            System.out.println("Please enter a text file and key formatted as: [program filename.txt key]");
            exit(1);
        }*/

        findShortestPaths(src, fileName);

    }


    /*
        we need to use an actual vertex for the graph because we will need to update the
        distances


     */

    public static String findShortestPaths(int src, String fileName) {
        String output = "";

        ArrayList<ArrayList<int[]>> graph = createGraph(fileName);
        Vertex[] vertices = new Vertex[graph.size()];

        initVertices(graph, src, vertices);

        ArrayList<Vertex> S = new ArrayList<>();
        MinHeap shortestPaths = new MinHeap(vertices);

        while (shortestPaths.size > 0) {
            Vertex u = shortestPaths.getSmallest();
            for (int[] pair : graph.get(u.id)) {
                Vertex cur = getVertex(vertices, pair[0]);
                if (cur.distance > u.distance + pair[1])
                    updateDistance(vertices, pair[0],u.distance + pair[1]);
                    updateDistance(shortestPaths.minHeap, pair[0],u.distance + pair[1]);
                    updateParent(shortestPaths, pair[0], u);
                    shortestPaths.buildMinHeap();
            }

        }

        for (Vertex vertex : vertices) {
            System.out.println(vertex.distance);
        }
        //System.out.println(shortestPaths.toString());

/*      Vertex getSmallest = shortestPaths.getSmallest();
        System.out.println("minheap after getting smallest: ");
        System.out.println(shortestPaths);

        System.out.println();*/
/*      System.out.print("vertices are: " );
        for (Vertex vertex : vertices){
            System.out.print(vertex + " " + vertex.distance + ";");
        }
        System.out.println();*/
        //displayGraph(graph);

        return output;
    }

    public static Vertex getVertex(Vertex[] vertices, int pos) {
        if (vertices.length <= 0 || vertices == null) {
            return  null;

        }

        for (Vertex vertex : vertices) {
            if (vertex.id == pos)
                return vertex;
        }


        System.out.println("vertex not in list");
        return null;

    }

    public static void updateDistance(Vertex[] vertices, int pos, int weight) {
        if (vertices.length == 0 || vertices == null) {
            return;
        }

        for (Vertex vertex : vertices) {
            if (vertex.id == pos)
                vertex.distance = weight;
                return;
        }


        System.out.println("couldn't find vertex in array");
    }

    public static void updateParent(MinHeap vertices, int pos, Vertex parent) {
        if (vertices.minHeap.length <= 0 || vertices == null) {
            return;
        }

        for (Vertex vertex : vertices.minHeap) {
            if (vertex.id == pos)
                vertex.parent = parent;
                return;
        }

        System.out.println("couldn't find vertex in array");
    }

    public static ArrayList<ArrayList<int[]>> createGraph(String fileName) {

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<int[]> newAdjList = new ArrayList<>();
                graph.add(newAdjList);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String[] vertexEdgeSegments = parts[1].split(";");

                    for (String segment : vertexEdgeSegments) {
                        String[] elements = segment.split(",");
                        int hop = Integer.parseInt(elements[0]);
                        int edge = Integer.parseInt(elements[1]);
                        int[] pair = {hop, edge};
                        Integer pos = Integer.parseInt(parts[0]);
                        graph.get(pos).add(pair);

                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return graph;

    }

    public static void displayGraph(ArrayList<ArrayList<int[]>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print(i+":");
            int counter = 0;
            for (int[] pair : graph.get(i)) {
                if (counter < graph.get(i).size()-1)
                    System.out.print(pair[0]+","+pair[1]+";");
                else
                    System.out.print(pair[0]+","+pair[1]);
                counter++;
            }
            System.out.println();

        }

    }

    private static void initVertices(ArrayList<ArrayList<int[]>> graph, int src, Vertex[] vertices) {
        for (int i = 0; i < graph.size(); i++) {
            Vertex newVertex = new Vertex(i);
            newVertex.id = i;

            if (i == src)
                newVertex.distance = 0;
            else
                newVertex.distance = Integer.MAX_VALUE;

            newVertex.parent = null;

            vertices[i] = newVertex;

        }
    }

}



