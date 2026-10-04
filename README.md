# DAA Assignment 2 - Data Structures

## Author

Yerkinbek Arsen

## Description

This project contains implementations of three data structures written from scratch:

- DynamicArray
- MyLinkedList
- MinHeap

The project also includes operation counters, benchmarks, JUnit tests, CSV results and plots.

## Project Structure

```text
src/main/java/
    structures/
        DynamicArray.java
        MyLinkedList.java
        MinHeap.java
    metrics/
        OperationMetrics.java
    benchmark/
        BenchmarkRunner.java

src/test/java/
    structures/
        DynamicArrayTest.java
        MyLinkedListTest.java
        MinHeapTest.java

results/
    results.csv
    plots/

REPORT.md
README.md
pom.xml

Requirements
- Java 17 or newer
- Maven
- Python 3
- pandas
- matplotlib
Build Project
Run:
mvn clean compile

Run Tests
Run:
mvn test

Run Benchmark
Run:
mvn exec:java -Dexec.mainClass="benchmark.BenchmarkRunner"

Or run BenchmarkRunner directly from IntelliJ IDEA.
The benchmark generates:
results/results.csv

Generate Plots
Run:
python3 plot_results.py

The plots will be saved in:
results/plots/

Benchmark Workloads
W1 - Random Access
DynamicArray and MyLinkedList perform 10000 random get(index) operations.
W2 - Search
DynamicArray and MyLinkedList perform 1000 contains(x) queries.
W3 - Insert and Remove
Two variants are tested:
- head
- middle
Each variant performs 1000 insertions and 1000 removals.
W4 - Priority Processing
MinHeap inserts all values and extracts them in non-decreasing order.
Results
Benchmark results are stored in:
results/results.csv

Columns:
workload,variant,structure,n,time_ms,steps,moves,comparisons

Report
Full complexity analysis, loop invariant proofs, plots and discussion are available in:
REPORT.md