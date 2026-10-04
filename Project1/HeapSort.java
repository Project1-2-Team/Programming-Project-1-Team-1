package Project1;

public class HeapSort {
    public static void heapSort(int[] arr, ComparisonCounter counter) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, counter);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0, counter);
        }
    }

    private static void heapify(int[] arr, int n, int i, ComparisonCounter counter) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && counter.greaterThan(arr[left], arr[largest])) {
            largest = left;
        }

        if (right < n && counter.greaterThan(arr[right], arr[largest])) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest, counter);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
