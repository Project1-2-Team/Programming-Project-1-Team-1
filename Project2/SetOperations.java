package project2;

/*
 * Team name: TBD
 * Team members: Mubarak, Anupa, Jake
 * Course and section: CS 2430, section TBD
 * Project: Programming Project 2 – Fall 2026
 * Purpose: Ordinary set operations using Boolean arrays.
 */
public class SetOperations {
	/** Validates that both sets match the universal set size. */
	private static void validate(boolean[] a, boolean[] b) {
		if (a == null || b == null || a.length != b.length) {
			throw new IllegalArgumentException("Sets must be non-null and the same size.");
		}
	}

	/** Returns the complement of a set. */
	public static boolean[] complement(boolean[] a) {
		if (a == null) {
			throw new IllegalArgumentException("Set cannot be null.");
		}
		boolean[] result = new boolean[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = !a[i];
		}
		return result;
	}

	/** Returns the elements present in either set. */
	public static boolean[] union(boolean[] a, boolean[] b) {
		validate(a, b);
		boolean[] result = new boolean[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] || b[i];
		}
		return result;
	}

	/** Returns the elements present in both sets. */
	public static boolean[] intersection(boolean[] a, boolean[] b) {
		validate(a, b);
		boolean[] result = new boolean[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] && b[i];
		}
		return result;
	}

	/** Returns the elements present in a but not b. */
	public static boolean[] difference(boolean[] a, boolean[] b) {
		validate(a, b);
		boolean[] result = new boolean[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] && !b[i];
		}
		return result;
	}

	/** Returns the elements present in exactly one of the sets. */
	public static boolean[] symmetricDifference(boolean[] a, boolean[] b) {
		validate(a, b);
		boolean[] result = new boolean[a.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = a[i] != b[i];
		}
		return result;
	}
}