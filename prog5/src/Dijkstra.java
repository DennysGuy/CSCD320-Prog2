import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class Dijkstra {
    public static void main(String[] args) {

        String fileName = "D:\\SCHOOL\\2024\\Winter Quarter\\CSCD 320\\Programming_Assignments\\CSCD_prog_assignment_5\\prog5\\src\\test.txt";
        int src = 0;


        ArrayList<Map<Vertex, Vertex>> graph = createGraph(fileName);

        displayGraph(graph);

/*        if (args.length == 2) {
            fileName = args[0];
            src = Integer.parseInt(args[1]);
        } else {
            System.out.println("Please enter a text file and key formatted as: [program filename.txt key]");
            exit(1);
        }*/

    }


    public static ArrayList<Map<Vertex,Vertex>> createGraph(String fileName) {

        ArrayList<Map<Vertex,Vertex>> graph = new ArrayList<>();

        int vertices = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {

                Map<Vertex, Vertex> temp = new HashMap<>();

                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String[] vertexEdgeSegments = parts[1].split(";");

                    for (String segment : vertexEdgeSegments) {
                        String[] elements = segment.split(",");
                        Vertex hop = new Vertex(Integer.parseInt(elements[0]));
                        Vertex edge = new Vertex(Integer.parseInt(elements[1]));
                        temp.put(hop,edge);
                    }

                    graph.add(temp);
                }

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return graph;

    }

    public static void displayGraph(ArrayList<Map<Vertex,Vertex>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print(i + graph.get(i).toString());
            System.out.println();
        }


    }
}


