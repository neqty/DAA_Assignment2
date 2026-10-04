package structures;

import metrics.OperationMetrics;

public class MinHeap {
    private int[] heap;
    private int size;
    private final OperationMetrics metrics;

    public MinHeap() {
        heap = new int[4];
        size = 0;
        metrics = new OperationMetrics();
    }

    public void insert(int x) {
        if (size == heap.length) {
            grow();
        }

        heap[size] = x;
        metrics.addMove();

        int index = size;
        size++;

        while (index > 0) {
            int parent = (index - 1) / 2;

            metrics.addStep();
            metrics.addStep();
            metrics.addComparison();

            if (heap[parent] <= heap[index]) {
                break;
            }

            int temp = heap[parent];
            heap[parent] = heap[index];
            heap[index] = temp;

            metrics.addMove();
            metrics.addMove();
            metrics.addMove();

            index = parent;
        }
    }

    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException();
        }

        metrics.addStep();
        return heap[0];
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException();
        }

        metrics.addStep();
        int min = heap[0];

        metrics.addStep();
        heap[0] = heap[size - 1];
        metrics.addMove();

        size--;

        int index = 0;

        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size) {
                metrics.addStep();
                metrics.addStep();
                metrics.addComparison();

                if (heap[left] < heap[smallest]) {
                    smallest = left;
                }
            }

            if (right < size) {
                metrics.addStep();
                metrics.addStep();
                metrics.addComparison();

                if (heap[right] < heap[smallest]) {
                    smallest = right;
                }
            }

            if (smallest == index) {
                break;
            }

            int temp = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = temp;

            metrics.addMove();
            metrics.addMove();
            metrics.addMove();

            index = smallest;
        }

        return min;
    }

    public int size() {
        return size;
    }

    public OperationMetrics getMetrics() {
        return metrics;
    }

    public void resetMetrics() {
        metrics.reset();
    }

    private void grow() {
        int[] newHeap = new int[heap.length * 2];

        for (int i = 0; i < heap.length; i++) {
            metrics.addStep();
            metrics.addMove();

            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }
}