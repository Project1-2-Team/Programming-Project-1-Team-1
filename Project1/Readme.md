# Programming Project 1: Algorithm Performance_PLO-CS-3

## Team 1

### Team Members
- Mubarak Alrashdi
- Anupa Dulal
- Jacob Gorham
- Victor Mai

### Course
CS-2430-502, Fall 2026

### Project
Programming Project 1: Algorithm Performance_PLO-CS-3

---

## Project Description

This project compares four sorting algorithms:

- Merge Sort
- Quick Sort
- Shaker Sort
- Heap Sort

The main measurement used in this project is the number of element-to-element comparisons made by each sorting algorithm.

The project generates all permutations of the values from `0` to `n-1` and tests each permutation using all four sorting algorithms.

The required input sizes are:

- `n = 4`
- `n = 6`
- `n = 8`

For each algorithm, the program reports:

- Best 10 cases
- Worst 10 cases
- Average number of comparisons

---

## Project Files

### Java Source Files

- `ComparisonCounter.java` - Counts element-to-element comparisons.
- `ExperimentalRuns.java` - Runs the experiments for `n = 4`, `n = 6`, and `n = 8`.
- `LexicographicalPermutationAlgorithm.java` - Generates all permutations.
- `MergeSort.java` - Merge Sort implementation.
- `QuickSort.java` - Quick Sort implementation.
- `ShakerSort.java` - Shaker Sort implementation.
- `HeapSort.java` - Heap Sort implementation.
- `TestDriver.java` - Used for testing the project.

### Documentation

- `README.md` - Explains the project and how to run it.
- `CONTRIBUTIONS.md` - Records team member contributions.
- `/docs/Project_Plan.md` - Project planning information.
- `/docs/Design_Notes.md` - Design and implementation notes.

---

## Requirements

To run this project, you need:

- Java JDK installed on your computer.
- A terminal or command prompt.
- The project files from this GitHub repository.

---

## Download the Project

### Option 1: Download ZIP

1. Open this GitHub repository.
2. Click the green **Code** button.
3. Select **Download ZIP**.
4. Extract the ZIP file.
5. Open a terminal in the folder containing the Java source files.

### Option 2: Clone the Repository

Use:

```bash
git clone https://github.com/Project1-2-Team/Programming-Project-1-Team-1.git
