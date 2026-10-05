// Team: Team 1
// Team Members: Mubarak Alrashdi, Anupa Dulal, Jacob Gorham, Victor Mai
// Course: CS-2430
// Section: 502
// Project: Programming Project 1: Algorithm Performance_PLO-CS-3
    
package Project1;

import java.util.*;

class Result {
    String algorithm;
    int[] input;
    int comparisons;

    Result(String algorithm, int[] input, int comparisons) {
        this.algorithm = algorithm;
        this.input = Arrays.copyOf(input, input.length);
        this.comparisons = comparisons;
    }
}

public class ExperimentalRuns {

    public static void main(String[] args) {
        int[] ns = {4, 6, 8};

        for (int n : ns) {
            System.out.println("Experimental Results for n = " + n + ":");
            List<int[]> permutations = LexicographicalPermutationAlgorithm.generatePermutations(n);

            runExperiments(permutations, n);
            System.out.println();
        }
    }

    private static void runExperiments(List<int[]> permutations, int n) {
        String[] algorithms = {"MergeSort", "QuickSort", "ShakerSort", "HeapSort"};

        for (String algorithm : algorithms) {
            List<Result> results = new ArrayList<>();
            ComparisonCounter counter = new ComparisonCounter();

            for (int[] permutation : permutations) {
                int[] arr = permutation.clone();
                counter.resetCount();

                switch (algorithm) {
                    case "MergeSort": MergeSort.mergeSort(arr, counter); break;
                    case "QuickSort": QuickSort.quickSort(arr, counter); break;
                    case "ShakerSort": ShakerSort.shakerSort(arr, counter); break;
                    case "HeapSort": HeapSort.heapSort(arr, counter); break;
                }

                results.add(new Result(algorithm, permutation, counter.getCount()));
            }

            summarizeResults(results, algorithm, n);
        }
    }

    private static void summarizeResults(List<Result> results, String algorithm, int n) {
        results.sort(Comparator.comparingInt(r -> r.comparisons));

        System.out.println(algorithm + ": Best 10 cases (n=" + n + "):");
        for (int i = 0; i < Math.min(10, results.size()); i++) {
            Result r = results.get(i);
            System.out.println("Comparisons: " + r.comparisons + ", Input: " + Arrays.toString(r.input));
        }

        System.out.println(algorithm + ": Worst 10 cases (n=" + n + "):");
        for (int i = results.size() - 1; i >= Math.max(results.size() - 10, 0); i--) {
            Result r = results.get(i);
            System.out.println("Comparisons: " + r.comparisons + ", Input: " + Arrays.toString(r.input));
        }

        double avg = results.stream().mapToInt(r -> r.comparisons).average().orElse(0);
        System.out.println(algorithm + ": Average comparisons (n=" + n + "): " + avg);
    }
}
