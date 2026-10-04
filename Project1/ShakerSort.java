package Project1;

public class ShakerSort {
    public static void shakerSort(int[] arr, ComparisonCounter counter) {
        boolean swapped;
        int left = 0;
        int right = arr.length - 1;

        do {
            swapped = false;

            for (int i = left; i < right; i++) {
                if (counter.greaterThan(arr[i], arr[i + 1])) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
            right--;

            for (int i = right; i > left; i--) {
                if (counter.lessThan(arr[i], arr[i - 1])) {
                    swap(arr, i, i - 1);
                    swapped = true;
                }
            }
            left++;
        } while (swapped);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
