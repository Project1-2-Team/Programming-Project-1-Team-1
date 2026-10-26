package Project2;

/*
 * Team name: TBD
 * Team members: Mubarak, Anupa, Jake
 * Course and section: CS 2430, section TBD
 * Project: Programming Project 2 – Fall 2026
 * Purpose: Dependency-free regression and fuzzy-set verification tests.
 */
public class Project2Tests {
	private static final double TOLERANCE = 0.000000001;
	private static int passed = 0;
	private static final String[] U = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

	/** Prints expected and actual values, and stops if a test fails. */
	private static void check(String name, double expected, double actual) {
		boolean ok = Math.abs(expected - actual) <= TOLERANCE;
		System.out.printf(java.util.Locale.US, "%s | expected: %.3f | actual: %.3f | %s%n",
			name, expected, actual, ok ? "PASS" : "FAIL");
		if (!ok) {
			throw new AssertionError(name);
		}
		passed++;
	}

	/** Checks that invalid inputs are rejected with an exception. */
	private static void expectInvalid(String name, Runnable operation) {
		boolean rejected = false;
		try {
			operation.run();
		} catch (IllegalArgumentException exception) {
			rejected = true;
		}
		System.out.println(name + " | expected: rejected | actual: "
			+ (rejected ? "rejected | PASS" : "accepted | FAIL"));
		if (!rejected) {
			throw new AssertionError(name);
		}
		passed++;
	}

	/** Runs the original operations again and tests new fuzzy behavior. */
	public static void main(String[] args) {
		System.out.println("TEST RESULTS | floating-point tolerance = " + TOLERANCE);
		boolean[] a = {true, false, true};
		boolean[] b = {false, true, true};
		check("Set complement", 0, SetOperations.complement(a)[0] ? 1 : 0);
		check("Set union", 1, SetOperations.union(a, b)[1] ? 1 : 0);
		check("Set intersection", 1, SetOperations.intersection(a, b)[2] ? 1 : 0);
		check("Set difference", 1, SetOperations.difference(a, b)[0] ? 1 : 0);
		check("Set symmetric difference", 0, SetOperations.symmetricDifference(a, b)[2] ? 1 : 0);
		check("Set empty complement", 1, SetOperations.complement(new boolean[3])[1] ? 1 : 0);
		int[] m = {2, 3, 0};
		int[] n = {1, 4, 2};
		check("Multiset union", 4, MultisetOperations.union(m, n)[1]);
		check("Multiset intersection", 1, MultisetOperations.intersection(m, n)[0]);
		check("Multiset difference", 0, MultisetOperations.difference(m, n)[1]);
		check("Multiset sum", 7, MultisetOperations.sum(m, n)[1]);
		check("Multiset empty difference", 0, MultisetOperations.difference(new int[3], n)[2]);
		FuzzySet fa = new FuzzySet(U);
		FuzzySet fb = new FuzzySet(U);
		fa.setMembership("a", 0.70);
		fa.setMembership("b", 1.00);
		fa.setMembership("c", 0.25);
		fb.setMembership("a", 0.40);
		fb.setMembership("d", 0.60);
		fb.setMembership("e", 1.00);
		check("Fuzzy retrieval intermediate", 0.70, fa.getMembership("a"));
		check("Fuzzy retrieval 1.0", 1.0, fa.getMembership("b"));
		check("Fuzzy missing entry 0.0", 0.0, fa.getMembership("j"));
		check("Fuzzy complement overlap", 0.30, fa.complement().getMembership("a"));
		check("Fuzzy complement missing", 1.0, fa.complement().getMembership("j"));
		check("Fuzzy complement 1.0", 0.0, fa.complement().getMembership("b"));
		check("Fuzzy union overlap", 0.70, fa.union(fb).getMembership("a"));
		check("Fuzzy intersection overlap", 0.40, fa.intersection(fb).getMembership("a"));
		check("Fuzzy union A only", 0.25, fa.union(fb).getMembership("c"));
		check("Fuzzy union B only", 0.60, fa.union(fb).getMembership("d"));
		check("Fuzzy intersection A only", 0.0, fa.intersection(fb).getMembership("c"));
		check("Fuzzy intersection B only", 0.0, fa.intersection(fb).getMembership("d"));
		check("Fuzzy union neither", 0.0, fa.union(fb).getMembership("j"));
		check("Fuzzy intersection neither", 0.0, fa.intersection(fb).getMembership("j"));
		expectInvalid("Reject negative degree", () -> fa.setMembership("a", -0.1));
		expectInvalid("Reject degree over 1", () -> fa.setMembership("a", 1.1));
		expectInvalid("Reject NaN", () -> fa.setMembership("a", Double.NaN));
		expectInvalid("Reject infinity", () -> fa.setMembership("a", Double.POSITIVE_INFINITY));
		expectInvalid("Reject unknown element", () -> fa.getMembership("unknown"));
		expectInvalid("Reject mismatched universe", () -> fa.union(new FuzzySet(new String[] {"x", "y"})));
		System.out.println("TOTAL: " + passed + " tests passed.");
	}
}