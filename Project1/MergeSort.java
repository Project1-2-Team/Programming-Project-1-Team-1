// Team: Team 1
// Team Members: Mubarak Alrashdi, Anupa Dulal, Jacob Gorham, Victor Mai
// Course: CS-2430
// Section: 502
// Project: Programming Project 1: Algorithm Performance_PLO-CS-3

package Project1;

public class MergeSort {
    public static void mergeSort(int[] arr, ComparisonCounter counter) {
        mergeSort(arr, 0, arr.length - 1, counter);
    }

    private static void mergeSort(int[] arr, int left, int right, ComparisonCounter counter) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid, counter);
            mergeSort(arr, mid + 1, right, counter);

            merge(arr, left, mid, right, counter);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, ComparisonCounter counter) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (counter.lessThan(arr[i], arr[j])) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}
