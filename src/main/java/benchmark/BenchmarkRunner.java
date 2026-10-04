package benchmark;

import structures.DynamicArray;
import structures.MyLinkedList;
import structures.MinHeap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    private static final int[] SIZES = {100, 1000, 10000, 100000};

    public static void main(String[] args) throws IOException {
        File resultsDir = new File("results");
        if (!resultsDir.exists()) {
            resultsDir.mkdirs();
        }

        FileWriter writer = new FileWriter("results/results.csv");

        writer.write("workload,variant,structure,n,time_ms,steps,moves,comparisons\n");

        for (int n : SIZES) {
            runW1(n, writer);
            runW2(n, writer);
            runW3Head(n, writer);
            runW3Middle(n, writer);
            runW4(n, writer);
        }

        writer.close();

        System.out.println("Benchmark finished");
        System.out.println("results/results.csv created");
    }

    private static int[] generateData(int n) {
        Random random = new Random(42);
        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt(1_000_000);
        }

        return data;
    }

    private static void runW1(int n, FileWriter writer) throws IOException {
        int[] data = generateData(n);

        long[] arrayTimes = new long[5];
        long[] listTimes = new long[5];

        long arraySteps = 0;
        long arrayMoves = 0;
        long arrayComparisons = 0;

        long listSteps = 0;
        long listMoves = 0;
        long listComparisons = 0;

        for (int run = 0; run < 5; run++) {
            DynamicArray array = new DynamicArray();
            MyLinkedList list = new MyLinkedList();

            for (int value : data) {
                array.add(value);
                list.add(value);
            }

            array.resetMetrics();
            list.resetMetrics();

            Random random = new Random(42);

            long start = System.nanoTime();

            for (int i = 0; i < 10000; i++) {
                array.get(random.nextInt(n));
            }

            long end = System.nanoTime();
            arrayTimes[run] = end - start;

            if (run == 4) {
                arraySteps = array.getMetrics().getSteps();
                arrayMoves = array.getMetrics().getMoves();
                arrayComparisons = array.getMetrics().getComparisons();
            }

            random = new Random(42);

            start = System.nanoTime();

            for (int i = 0; i < 10000; i++) {
                list.get(random.nextInt(n));
            }

            end = System.nanoTime();
            listTimes[run] = end - start;

            if (run == 4) {
                listSteps = list.getMetrics().getSteps();
                listMoves = list.getMetrics().getMoves();
                listComparisons = list.getMetrics().getComparisons();
            }
        }

        double arrayMedian = medianWithoutWarmup(arrayTimes);
        double listMedian = medianWithoutWarmup(listTimes);

        writeRow(writer, "W1", "-", "DynamicArray", n,
                arrayMedian, arraySteps, arrayMoves, arrayComparisons);

        writeRow(writer, "W1", "-", "MyLinkedList", n,
                listMedian, listSteps, listMoves, listComparisons);
    }

    private static void runW2(int n, FileWriter writer) throws IOException {
        int[] data = generateData(n);

        long[] arrayTimes = new long[5];
        long[] listTimes = new long[5];

        long arraySteps = 0;
        long arrayMoves = 0;
        long arrayComparisons = 0;

        long listSteps = 0;
        long listMoves = 0;
        long listComparisons = 0;

        int[] queries = new int[1000];

        for (int i = 0; i < 500; i++) {
            queries[i] = data[i % n];
        }

        for (int i = 500; i < 1000; i++) {
            queries[i] = -i - 1;
        }

        for (int run = 0; run < 5; run++) {
            DynamicArray array = new DynamicArray();
            MyLinkedList list = new MyLinkedList();

            for (int value : data) {
                array.add(value);
                list.add(value);
            }

            array.resetMetrics();
            list.resetMetrics();

            long start = System.nanoTime();

            for (int q : queries) {
                array.contains(q);
            }

            long end = System.nanoTime();
            arrayTimes[run] = end - start;

            if (run == 4) {
                arraySteps = array.getMetrics().getSteps();
                arrayMoves = array.getMetrics().getMoves();
                arrayComparisons = array.getMetrics().getComparisons();
            }

            start = System.nanoTime();

            for (int q : queries) {
                list.contains(q);
            }

            end = System.nanoTime();
            listTimes[run] = end - start;

            if (run == 4) {
                listSteps = list.getMetrics().getSteps();
                listMoves = list.getMetrics().getMoves();
                listComparisons = list.getMetrics().getComparisons();
            }
        }

        writeRow(writer, "W2", "-", "DynamicArray", n,
                medianWithoutWarmup(arrayTimes),
                arraySteps, arrayMoves, arrayComparisons);

        writeRow(writer, "W2", "-", "MyLinkedList", n,
                medianWithoutWarmup(listTimes),
                listSteps, listMoves, listComparisons);
    }

    private static void runW3Head(int n, FileWriter writer) throws IOException {
        int[] data = generateData(n);

        long[] arrayTimes = new long[5];
        long[] listTimes = new long[5];

        long arraySteps = 0;
        long arrayMoves = 0;
        long arrayComparisons = 0;

        long listSteps = 0;
        long listMoves = 0;
        long listComparisons = 0;

        for (int run = 0; run < 5; run++) {
            DynamicArray array = new DynamicArray();
            MyLinkedList list = new MyLinkedList();

            for (int value : data) {
                array.add(value);
                list.add(value);
            }

            array.resetMetrics();
            list.resetMetrics();

            long start = System.nanoTime();

            for (int i = 0; i < 1000; i++) {
                array.add(0, i);
            }

            for (int i = 0; i < 1000; i++) {
                array.remove(0);
            }

            long end = System.nanoTime();
            arrayTimes[run] = end - start;

            if (run == 4) {
                arraySteps = array.getMetrics().getSteps();
                arrayMoves = array.getMetrics().getMoves();
                arrayComparisons = array.getMetrics().getComparisons();
            }

            start = System.nanoTime();

            for (int i = 0; i < 1000; i++) {
                list.add(0, i);
            }

            for (int i = 0; i < 1000; i++) {
                list.remove(0);
            }

            end = System.nanoTime();
            listTimes[run] = end - start;

            if (run == 4) {
                listSteps = list.getMetrics().getSteps();
                listMoves = list.getMetrics().getMoves();
                listComparisons = list.getMetrics().getComparisons();
            }
        }

        writeRow(writer, "W3", "head", "DynamicArray", n,
                medianWithoutWarmup(arrayTimes),
                arraySteps, arrayMoves, arrayComparisons);

        writeRow(writer, "W3", "head", "MyLinkedList", n,
                medianWithoutWarmup(listTimes),
                listSteps, listMoves, listComparisons);
    }

    private static void runW3Middle(int n, FileWriter writer) throws IOException {
        int[] data = generateData(n);

        long[] arrayTimes = new long[5];
        long[] listTimes = new long[5];

        long arraySteps = 0;
        long arrayMoves = 0;
        long arrayComparisons = 0;

        long listSteps = 0;
        long listMoves = 0;
        long listComparisons = 0;

        for (int run = 0; run < 5; run++) {
            DynamicArray array = new DynamicArray();
            MyLinkedList list = new MyLinkedList();

            for (int value : data) {
                array.add(value);
                list.add(value);
            }

            array.resetMetrics();
            list.resetMetrics();

            int middle = n / 2;

            long start = System.nanoTime();

            for (int i = 0; i < 1000; i++) {
                array.add(middle, i);
            }

            for (int i = 0; i < 1000; i++) {
                array.remove(middle);
            }

            long end = System.nanoTime();
            arrayTimes[run] = end - start;

            if (run == 4) {
                arraySteps = array.getMetrics().getSteps();
                arrayMoves = array.getMetrics().getMoves();
                arrayComparisons = array.getMetrics().getComparisons();
            }

            start = System.nanoTime();

            for (int i = 0; i < 1000; i++) {
                list.add(middle, i);
            }

            for (int i = 0; i < 1000; i++) {
                list.remove(middle);
            }

            end = System.nanoTime();
            listTimes[run] = end - start;

            if (run == 4) {
                listSteps = list.getMetrics().getSteps();
                listMoves = list.getMetrics().getMoves();
                listComparisons = list.getMetrics().getComparisons();
            }
        }

        writeRow(writer, "W3", "middle", "DynamicArray", n,
                medianWithoutWarmup(arrayTimes),
                arraySteps, arrayMoves, arrayComparisons);

        writeRow(writer, "W3", "middle", "MyLinkedList", n,
                medianWithoutWarmup(listTimes),
                listSteps, listMoves, listComparisons);
    }

    private static void runW4(int n, FileWriter writer) throws IOException {
        int[] data = generateData(n);

        long[] times = new long[5];

        long steps = 0;
        long moves = 0;
        long comparisons = 0;

        for (int run = 0; run < 5; run++) {
            MinHeap heap = new MinHeap();

            heap.resetMetrics();

            long start = System.nanoTime();

            for (int value : data) {
                heap.insert(value);
            }

            int previous = Integer.MIN_VALUE;

            while (heap.size() > 0) {
                int current = heap.extractMin();

                if (current < previous) {
                    throw new IllegalStateException("Heap output is not sorted");
                }

                previous = current;
            }

            long end = System.nanoTime();
            times[run] = end - start;

            if (run == 4) {
                steps = heap.getMetrics().getSteps();
                moves = heap.getMetrics().getMoves();
                comparisons = heap.getMetrics().getComparisons();
            }
        }

        writeRow(writer, "W4", "-", "MinHeap", n,
                medianWithoutWarmup(times),
                steps, moves, comparisons);
    }

    private static double medianWithoutWarmup(long[] times) {
        long[] values = {
                times[1],
                times[2],
                times[3],
                times[4]
        };

        Arrays.sort(values);

        double medianNs = (values[1] + values[2]) / 2.0;

        return medianNs / 1_000_000.0;
    }

    private static void writeRow(
            FileWriter writer,
            String workload,
            String variant,
            String structure,
            int n,
            double timeMs,
            long steps,
            long moves,
            long comparisons) throws IOException {

        writer.write(
                workload + "," +
                        variant + "," +
                        structure + "," +
                        n + "," +
                        String.format(java.util.Locale.US, "%.4f", timeMs) + "," +
                        steps + "," +
                        moves + "," +
                        comparisons + "\n"
        );
    }
}