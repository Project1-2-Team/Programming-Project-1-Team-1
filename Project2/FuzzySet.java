package Project2;

/*
 * Team name: TBD
 * Team members: Mubarak, Anupa, Jake
 * Course and section: CS 2430, section TBD
 * Project: Programming Project 2 – Fall 2026
 * Purpose: Fuzzy-set membership and operations over a fixed universe.
 */
public class FuzzySet {
	private final String[] universe;
	private final double[] degrees;

	/** Creates an empty fuzzy set; all elements initially have degree 0.0. */
	public FuzzySet(String[] elements) {
		if (elements == null || elements.length == 0) {
			throw new IllegalArgumentException("Universe cannot be empty.");
		}
		universe = elements.clone();
		degrees = new double[elements.length];
		for (int i = 0; i < universe.length; i++) {
			if (universe[i] == null || universe[i].isEmpty()) {
				throw new IllegalArgumentException("Element names must be nonempty.");
			}
			for (int j = 0; j < i; j++) {
				if (universe[i].equals(universe[j])) {
					throw new IllegalArgumentException("Duplicate universe element: " + universe[i]);
				}
			}
		}
	}

	/** Finds the position of a named element in the fixed universe. */
	private int indexOf(String element) {
		for (int i = 0; i < universe.length; i++) {
			if (universe[i].equals(element)) {
				return i;
			}
		}
		throw new IllegalArgumentException("Element not in universe: " + element);
	}

	/** Stores a finite membership degree between 0.0 and 1.0 inclusive. */
	public void setMembership(String element, double degree) {
		int index = indexOf(element);
		if (!Double.isFinite(degree) || degree < 0.0 || degree > 1.0) {
			throw new IllegalArgumentException("Membership must be between 0.0 and 1.0.");
		}
		degrees[index] = degree;
	}

	/** Returns 0.0 for any element in the universe not explicitly assigned. */
	public double getMembership(String element) {
		return degrees[indexOf(element)];
	}

	/** Ensures both operands use exactly the same universe and ordering. */
	private void validatePair(FuzzySet other) {
		if (other == null || universe.length != other.universe.length) {
			throw new IllegalArgumentException("Fuzzy sets must use the same universe.");
		}
		for (int i = 0; i < universe.length; i++) {
			if (!universe[i].equals(other.universe[i])) {
				throw new IllegalArgumentException("Universe ordering must match.");
			}
		}
	}

	/** Complements every element of the universe, including missing entries. */
	public FuzzySet complement() {
		FuzzySet result = new FuzzySet(universe);
		for (int i = 0; i < universe.length; i++) {
			result.degrees[i] = 1.0 - degrees[i];
		}
		return result;
	}

	/** Uses the larger membership degree for each element. */
	public FuzzySet union(FuzzySet other) {
		validatePair(other);
		FuzzySet result = new FuzzySet(universe);
		for (int i = 0; i < universe.length; i++) {
			result.degrees[i] = Math.max(degrees[i], other.degrees[i]);
		}
		return result;
	}

	/** Uses the smaller membership degree for each element. */
	public FuzzySet intersection(FuzzySet other) {
		validatePair(other);
		FuzzySet result = new FuzzySet(universe);
		for (int i = 0; i < universe.length; i++) {
			result.degrees[i] = Math.min(degrees[i], other.degrees[i]);
		}
		return result;
	}

	/** Formats every universe element with its membership degree. */
	public String toString() {
		StringBuilder result = new StringBuilder("{");
		for (int i = 0; i < universe.length; i++) {
			if (i > 0) {
				result.append(", ");
			}
			result.append(universe[i]).append(":")
				.append(String.format(java.util.Locale.US, "%.2f", degrees[i]));
		}
		return result.append("}").toString();
	}
}