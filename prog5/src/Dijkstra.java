import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dijkstra {
    public static void main(String[] args) {

        String fileName = "C:\\SCHOOL\\Winter 2024\\CSCD 320\\programming_assignment_2\\CSCD320-Prog2\\prog5\\src\\test.txt";
        int src = 0;

        /*if (args.length == 2) {
            fileName = args[0];
            src = Integer.parseInt(args[1]);
        } else {
            System.out.println("Please enter a text file and key formatted as: [program filename.txt key]");
            exit(1);
        }*/

        ArrayList<ArrayList<Vertex[]>> graph = createGraph(fileName);


        displayGraph(graph);
    }

    public String findShortestPaths(ArrayList<ArrayList<Vertex[]>> graph, int src) {
        String output = "";

        return output;
    }

    public static ArrayList<ArrayList<Vertex[]>> createGraph(String fileName) {

        ArrayList<ArrayList<Vertex[]>> graph = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Vertex[]> newAdjList = new ArrayList<>();
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
                        Vertex hop = new Vertex(Integer.parseInt(elements[0]));
                        Vertex edge = new Vertex(Integer.parseInt(elements[1]));
                        Vertex[] pair = {hop, edge};
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

    public static void displayGraph(ArrayList<ArrayList<Vertex[]>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print(i+":");
            int counter = 0;
            for (Vertex[] pair : graph.get(i)) {
                if (counter < graph.get(i).size()-1)
                    System.out.print(pair[0].id+","+pair[1].id+";");
                else
                    System.out.print(pair[0].id+","+pair[1].id);
                counter++;
            }
            System.out.println();

        }

    }


}



