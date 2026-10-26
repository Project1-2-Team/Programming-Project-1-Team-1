package Project2;

/*
 * Team name: TBD
 * Team members: Mubarak, Anupa, Jake
 * Course and section: CS 2430, section TBD
 * Project: Programming Project 2 – Fall 2026
 * Purpose: Multiset operations using non-negative occurrence counts.
 */
public class MultisetOperations {
	/** Rejects missing arrays, mismatched sizes, and negative counts. */
	public static void validate(int[] counts) {
		if (counts == null) {
			throw new IllegalArgumentException("Multiset cannot be null.");
		}
		for (int count : counts) {
			if (count < 0) {
				throw new IllegalArgumentException("Counts cannot be negative.");
			}
		}
	}

	private static void validatePair(int[] a, int[] b) {
		validate(a);
		validate(b);
		if (a.length != b.length) {
			throw new IllegalArgumentException("Multisets must have the same size.");
		}
	}

	/** Takes the larger count at each position. */
	public static int[] union(int[] a, int[] b) {
		validatePair(a, b);
		int[] result = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = Math.max(a[i], b[i]);
		}
		return result;
	}

	/** Takes the smaller count at each position. */
	public static int[] intersection(int[] a, int[] b) {
		validatePair(a, b);
		int[] result = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = Math.min(a[i], b[i]);
		}
		return result;
	}

	/** Subtracts counts without going below zero. */
	public static int[] difference(int[] a, int[] b) {
		validatePair(a, b);
		int[] result = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = Math.max(0, a[i] - b[i]);
		}
		return result;
	}

	/** Adds counts at each position. */
	public static int[] sum(int[] a, int[] b) {
		validatePair(a, b);
		int[] result = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = Math.addExact(a[i], b[i]);
		}
		return result;
	}
}