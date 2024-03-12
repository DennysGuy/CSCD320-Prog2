public class MinHeap {



    public static void buildMinHeap(int[] array, int heapSize) {
        for (int i = (int) Math.floor(heapSize / 2.0) - 1; i >= 0; i--) {
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
