# SortingResearch
Java implementation of a hybrid sorting algorithm combining Quick Sort and Insertion Sort, along with standalone Quick Sort and Insertion Sort. Includes performance analysis across varying array sizes.

# Sorting Algorithm Performance Analysis
A Java-based study comparing **QuickSort**, **InsertionSort**, and a **Hybrid Sorting Algorithm**.

## How to Run
1. Compile the code: `javac comparesSort.java`
2. Run the benchmark: `java comparesSort`

## Features
- **Median-of-Three Pivot**: Optimized QuickSort to avoid O(n²) worst-cases.
- **Hybrid Strategy**: Switches to InsertionSort at a threshold of 20 elements.
- **Scientific Benchmarking**: Includes JVM warm-up and averaged runs for statistical accuracy.
