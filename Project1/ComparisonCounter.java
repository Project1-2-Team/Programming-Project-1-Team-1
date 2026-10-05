# Team: Team 1
# Team Members: Mubarak Alrashdi, Anupa Dulal, Jacob Gorham, Victor Mai
# Course: CS-2430
# Section: 502
# Project: Programming Project 1: Algorithm Performance_PLO-CS-3
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
