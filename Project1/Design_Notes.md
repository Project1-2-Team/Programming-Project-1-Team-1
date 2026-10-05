# Design Notes

## Comparison Counter

The project uses a `ComparisonCounter` class to count element-to-element comparisons.

The counter increases when two values are compared for ordering.

Control checks such as loop conditions and array indexes are not included in the comparison count.

## Permutation Generation

The project uses a lexicographical permutation algorithm to generate all permutations of the values from 0 to n-1.

The program generates all permutations for:

- n = 4
- n = 6
- n = 8

## Sorting Algorithms

The project contains four sorting implementations:

- Merge Sort
- Quick Sort
- Shaker Sort
- Heap Sort

Each algorithm receives an input array and the comparison counter.

## Experimental Driver

`ExperimentalRuns.java` runs the experiment for n = 4, 6, and 8.

For each permutation, the program makes a copy of the input before sorting it.

This allows the same original permutation to be tested by each sorting algorithm.

The program records:

- Algorithm name
- Input permutation
- Number of comparisons

The results are then sorted by comparison count to identify the best and worst cases.

The average comparison count is also calculated.
