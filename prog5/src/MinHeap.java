import java.util.Arrays;

public class MinHeap {

    public Vertex[] minHeap;
    public int size;

    public MinHeap(Vertex[] array) {
        this.minHeap = array;
        this.size = array.length;
        buildMinHeap();
    }

    public void buildMinHeap() {
        int heapSize = minHeap.length;
        for (int i = (int) Math.floor(heapSize / 2.0) - 1; i >= 0; i--) {
            minHeapify(minHeap, i, heapSize);
        }
    }

    private void minHeapify(Vertex[] array, int index, int heapsize) {

        int l = 2*index + 1;
        int r = 2*index + 2;
        int smallest = index;

        if (l < heapsize && array[l].distance < array[index].distance) {
            smallest = l;
        }

        if (r < heapsize && array[r].distance < array[smallest].distance) {
            smallest = r;
        }

        if (smallest != index) {
            swap(array, index, smallest);
            minHeapify(array,smallest, heapsize);
        }


    }

    public int size() {
        return this.size;
    }

    //this will pop the smallest index from the heap and return it to the user
    //this will also make sure that the min heap is still a min heap
    public Vertex getSmallest() {
            Vertex smallest = this.minHeap[0];
            Vertex[] newMinHeap = Arrays.copyOfRange(this.minHeap,1,this.minHeap.length);

            this.size = newMinHeap.length;
            this.minHeap = newMinHeap;

            buildMinHeap();
            return smallest;
    }

    private void swap(Vertex[] array, int one, int two) {
        Vertex temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    private void heapSort(Vertex[] array, int heapSize) {

        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i); // Use 0-based index for swapping
            heapSize--;
            minHeapify(array, 0, heapSize); // Use 1-based index for minHeapify
        }


    }


    @Override
    public String toString(){
        String output = "";
        output += "vertices in minheap: ";
        for (Vertex vertex : this.minHeap) {
            output += (vertex +", ");
        }
        return output;
    }
}
