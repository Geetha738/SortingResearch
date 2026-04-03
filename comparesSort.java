import java.util.*;

public class comparesSort {

    static final int THRESHOLD = 20;
    static final int RUNS = 20;

    static Random rand = new Random(42);

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

  
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void insertionSortRange(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Median of Three Pivot
    public static int medianOfThree(int[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low] > arr[mid]) swap(arr, low, mid);
        if (arr[low] > arr[high]) swap(arr, low, high);
        if (arr[mid] > arr[high]) swap(arr, mid, high);

        return mid;
    }

    // Partition
    public static int partition(int[] arr, int low, int high) {
        int pivotIndex = medianOfThree(arr, low, high);
        int pivot = arr[pivotIndex];

        int i = low, j = high;

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi, high);
        }
    }

    // Hybrid Sort
    public static void hybridSort(int[] arr, int low, int high) {
        while (high - low > THRESHOLD) {
            int pi = partition(arr, low, high);

            if (pi - low < high - pi) {
                hybridSort(arr, low, pi - 1);
                low = pi;
            } else {
                hybridSort(arr, pi, high);
                high = pi - 1;
            }
        }
    }

    public static int[] generateArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000);
        }
        return arr;
    }

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 5000, 10000, 200000};

        // JVM Warm-up
        for (int i = 0; i < 5; i++) {
            int[] temp = generateArray(1000);
            quickSort(temp, 0, temp.length - 1);
        }

        for (int size : sizes) {

            long quickTotal = 0;
            long insertionTotal = 0;
            long hybridTotal = 0;

            for (int r = 0; r < RUNS; r++) {

                int[] original = generateArray(size);

                int[] arr1 = original.clone();
                int[] arr2 = original.clone();
                int[] arr3 = original.clone();

                long start = System.nanoTime();
                quickSort(arr1, 0, arr1.length - 1);
                quickTotal += (System.nanoTime() - start);

                start = System.nanoTime();
                insertionSort(arr2);
                insertionTotal += (System.nanoTime() - start);

                start = System.nanoTime();
                hybridSort(arr3, 0, arr3.length - 1);
                insertionSort(arr3); // Final pass
                hybridTotal += (System.nanoTime() - start);
            }

            System.out.println("Size: " + size);
            System.out.println("Quick Avg: " + (quickTotal / RUNS) + " ns");
            System.out.println("Insertion Avg: " + (insertionTotal / RUNS) + " ns");
            System.out.println("Hybrid Avg: " + (hybridTotal / RUNS) + " ns");
            System.out.println("-----------------------------");
        }
    }
}