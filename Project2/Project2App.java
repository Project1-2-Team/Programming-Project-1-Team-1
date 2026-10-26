package Project2;

/*
 * Team name: TBD
 * Team members: Mubarak, Anupa, Jake
 * Course and section: CS 2430, section TBD
 * Project: Programming Project 2 – Fall 2026
 * Purpose: Runs representative and edge cases for ordinary sets and multisets.
 */
public class Project2App {
	// Every array position corresponds to the same element in this fixed order.
	private static final String[] UNIVERSAL = {
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
	};

	/** Runs three examples for each part, including empty and equal sets. */
	public static void main(String[] args) {
		System.out.println("Universal set order: " + String.join(", ", UNIVERSAL));

		// Case 1: overlapping ordinary sets and multisets with repeated elements.
		runSetCase("Case 1: Overlap",
			new boolean[] {true, false, true, false, true, false, false, false, false, false},
			new boolean[] {false, true, true, false, false, true, false, false, false, false});
		runMultisetCase("Case 1: Repeated elements",
			new int[] {2, 3, 1, 0, 2, 0, 0, 0, 0, 0},
			new int[] {1, 2, 4, 0, 0, 3, 0, 0, 0, 0});

		// Case 2: empty A tests complements, unions, and zero-clamped differences.
		runSetCase("Case 2: Empty A",
			new boolean[UNIVERSAL.length],
			new boolean[] {true, false, false, false, false, false, false, false, false, true});
		runMultisetCase("Case 2: Empty A",
			new int[UNIVERSAL.length],
			new int[] {0, 0, 2, 0, 0, 0, 0, 0, 0, 3});

		// Case 3: equal inputs test intersection, symmetric difference, and subtraction.
		boolean[] sameSet = {true, true, false, false, false, false, true, false, false, false};
		int[] sameBag = {2, 0, 3, 0, 0, 0, 1, 0, 0, 0};
		runSetCase("Case 3: Equal sets", sameSet, sameSet);
		runMultisetCase("Case 3: Equal multisets", sameBag, sameBag);

		// Part 3: demonstrate fuzzy degrees, missing entries, and all operations.
		runFuzzyCase();
	}

	/** Demonstrates the new Week 2 fuzzy-set operations. */
	private static void runFuzzyCase() {
		FuzzySet a = new FuzzySet(UNIVERSAL);
		FuzzySet b = new FuzzySet(UNIVERSAL);
		a.setMembership("a", 0.70);
		a.setMembership("b", 1.00);
		a.setMembership("c", 0.25);
		b.setMembership("a", 0.40);
		b.setMembership("d", 0.60);
		b.setMembership("e", 1.00);
		System.out.println("\n=== PART 3: FUZZY SETS | Week 2 Change Order ===");
		System.out.println("A: " + a);
		System.out.println("B: " + b);
		System.out.println("NOT(A): " + a.complement());
		System.out.println("A UNION B (max): " + a.union(b));
		System.out.println("A INTERSECTION B (min): " + a.intersection(b));
		System.out.println("Lookup A(a): " + a.getMembership("a"));
		System.out.println("Lookup A(j), missing entry: " + a.getMembership("j"));
	}

	/** Prints all five ordinary set operations for a test case. */
	private static void runSetCase(String name, boolean[] a, boolean[] b) {
		checkLength(a.length);
		checkLength(b.length);
		System.out.println("\n=== PART 1: ORDINARY SETS | " + name + " ===");
		printSet("A", a);
		printSet("B", b);
		printSet("NOT(A)", SetOperations.complement(a));
		printSet("A UNION B", SetOperations.union(a, b));
		printSet("A INTERSECTION B", SetOperations.intersection(a, b));
		printSet("A - B", SetOperations.difference(a, b));
		printSet("A SYMMETRIC DIFFERENCE B", SetOperations.symmetricDifference(a, b));
	}

	/** Prints the bit string and the readable names of included elements. */
	private static void printSet(String label, boolean[] values) {
		StringBuilder bits = new StringBuilder();
		StringBuilder names = new StringBuilder("{");
		for (int i = 0; i < values.length; i++) {
			bits.append(values[i] ? '1' : '0');
			if (values[i]) {
				if (names.length() > 1) {
					names.append(", ");
				}
				names.append(UNIVERSAL[i]);
			}
		}
		names.append("}");
		System.out.println(label + " | bits: " + bits + " | elements: " + names);
	}

	/** Prints all four multiset operations for a test case. */
	private static void runMultisetCase(String name, int[] a, int[] b) {
		checkLength(a.length);
		checkLength(b.length);
		System.out.println("\n=== PART 2: MULTISETS | " + name + " ===");
		printMultiset("A", a);
		printMultiset("B", b);
		printMultiset("A UNION B (max)", MultisetOperations.union(a, b));
		printMultiset("A INTERSECTION B (min)", MultisetOperations.intersection(a, b));
		printMultiset("A - B (clamped)", MultisetOperations.difference(a, b));
		printMultiset("A + B (sum)", MultisetOperations.sum(a, b));
	}

	/** Prints nonzero multiplicities as element:count pairs. */
	private static void printMultiset(String label, int[] counts) {
		MultisetOperations.validate(counts);
		StringBuilder names = new StringBuilder("{");
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0) {
				if (names.length() > 1) {
					names.append(", ");
				}
				names.append(UNIVERSAL[i]).append(":").append(counts[i]);
			}
		}
		names.append("}");
		System.out.println(label + " | counts: " + names);
	}

	/** Ensures each example uses the documented universal set. */
	private static void checkLength(int length) {
		if (length != UNIVERSAL.length) {
			throw new IllegalArgumentException("Expected " + UNIVERSAL.length + " elements.");
		}
	}
}