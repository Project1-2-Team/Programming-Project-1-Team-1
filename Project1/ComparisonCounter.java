package Project1;

public class ComparisonCounter {
    private int count = 0;

    public boolean lessThan(int a, int b) {
        count++;
        return a < b;
    }

    public boolean greaterThan(int a, int b) {
        count++;
        return a > b;
    }

    public int getCount() {
        return count;
    }

    public void resetCount() {
        count = 0;
    }
}
