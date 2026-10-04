package Project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexicographicalPermutationAlgorithm {
    
    public static List<int[]> generatePermutations(int n) {
        List<int[]> permutations = new ArrayList<>();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        while (true) {
            permutations.add(Arrays.copyOf(arr, arr.length));

            int k = -1;
            for (int i = n - 2; i >= 0; i--) {
                if (arr[i] < arr[i + 1]) {
                    k = i;
                    break;
                }
            }
            if (k == -1) {
                break;
            }

            int l = -1;
            for (int i = n - 1; i > k; i--) {
                if (arr[i] > arr[k]) {
                    l = i;
                    break;
                }
            }

            int temp = arr[k];
            arr[k] = arr[l];
            arr[l] = temp;

            for (int i = k + 1, j = n - 1; i < j; i++, j--) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return permutations;
    }
}
