import java.io.*;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Richest {

    public static void main(String[] args) {
        String fileName = "";
        int key = 0;
        if (args.length == 1) {
            fileName = args[0];
        } else {
            System.out.println("Please enter a text file and key formatted as: [program filename.txt]");
            exit(1);
        }
        int[] heap = new int[10000];
        int heapSize = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);
                    if (heapSize < heap.length) {
                        heap[heapSize] = number;
                        heapSize++;
                        if (heapSize == heap.length) {
                            buildMinHeap(heap,heapSize);
                        }
                    } else if (number > heap[0]) {
                        heap[0] = number;
                        minHeapify(heap, 0, heapSize);
                    }

                } catch (NumberFormatException e){
                    System.err.println("Skipping non-integer value: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        heapSort(heap, heapSize);
        System.out.println(heapSize);

        fileName = "richest-top10k.output";
        writeToFile(fileName, heap.length, heap);
        fileName = "richest-top10.output";
        writeToFile(fileName, 10, heap);
    }

    public static void writeToFile(String fileName, int length, int[] heap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for(int i = 0; i < length; i++) {
                writer.write(Integer.toString(heap[i]));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void buildMinHeap(int[] array, int heapSize) {
        for (int i = (int)Math.floor(heapSize/2.0)-1; i >= 0; i--) {
            minHeapify(array, i, heapSize);
        }
    }

    public static void minHeapify(int[] array, int index, int heapsize) {

        int l = 2*index + 1;
        int r = 2*index + 2;
        int smallest = index;

        if (l < heapsize && array[l] < array[index]) {
            smallest = l;
        }

        if (r < heapsize && array[r] < array[smallest]) {
            smallest = r;
        }

        if (smallest != index) {
            swap(array, index, smallest);
            minHeapify(array,smallest, heapsize);
        }

    }

    public static void swap(int[] array, int one, int two) {
        int temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    public static void heapSort(int[] array, int heapSize) {

        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i); // Use 0-based index for swapping
            heapSize--;
            minHeapify(array, 0, heapSize); // Use 1-based index for minHeapify
        }


    }
}