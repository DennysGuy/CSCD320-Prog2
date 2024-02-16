import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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


        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int[] topTenThousand = new int[10000];

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);

                } catch (NumberFormatException e){
                    System.err.println("Skipping non-integer value: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void buildMinHeap(int[] topTenThousand) {
        /*
            -- build max heap pseudo code
            build_max_heap(A) {
                A.heap_size = A.length //A.length = n
                for (i = floor(A.length/2); i >= 1; i--)
                    Max_Heapify(A, i)
            }
         */

        int heapSize = topTenThousand.length;
        for (int i = (int)Math.floor(heapSize/2); i >= 1; i--) {
            minHeapify(topTenThousand, i);
        }


    }

    public static void minHeapify(int[] topTenThousand, int index) {
        /*
            -- max heap properties (change to min
                - the value of i <= value of parent (for min heap we do value of i >= value of parent)
                - value of i >= value of children (for min heap value of i <= value of children)

            -- max heapify pseudo code
            maxHeapify(A, i) {
                l = left(i) --> 2*i
                r = right(i)--> 2*i + 1
                largest = i
                if l <= A.heap-size and A[l] > A[i]
                    largest = l
                else
                    largest = i
                if r <= A.heap-size and A[r] > A[largest]
                    largest = r
                if largest != i
                    exchange A[i] with A[largest]
                    max-heapify(A, largest)

            }
         */

        int l = 2*index;
        int r = 2*index + 1;
        int smallest = index;

        if (l <= topTenThousand.length && topTenThousand[l] < topTenThousand[index]) {
            smallest = l;
        } else {
            smallest = index;
        }

        if (r <= topTenThousand.length && topTenThousand[r] < topTenThousand[smallest]) {
            smallest = r;
        }

        if (smallest != index) {
            int temp = topTenThousand[smallest];
            topTenThousand[smallest] = topTenThousand[index];
            topTenThousand[index] = temp;
            minHeapify(topTenThousand,smallest);
        }

    }

    public static void heapsort(int[] topTenThousand) {
        /*
            -- originally in ascending order, but we want decending order
            -- won't have to change anything because min heap will place things in descending order instead of ascending
            psuedocode for heapsort

            Heapsort(A)
            {
                Build_min_heap(A)
                for (i = A.length; i >= 2; i--) {
                     int temp = A[1];
                    A[1] = A[i];
                    A[i] = temp;
                    A.heap_size--;
                    min_heapify(A, 1)

                }

            }

         */
        buildMinHeap(topTenThousand);
        for (int i = topTenThousand.length; i >= 2; i--) {
            int temp = topTenThousand[1];
            topTenThousand[1] = topTenThousand[i];
            topTenThousand[i] = temp;

            minHeapify(topTenThousand, 1);
        }


    }
}