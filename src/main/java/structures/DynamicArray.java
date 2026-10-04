package structures;

import metrics.OperationMetrics;

public class DynamicArray {
    private int[] data;
    private int size;
    private final OperationMetrics metrics;

    public DynamicArray() {
        data = new int[4];
        size = 0;
        metrics = new OperationMetrics();
    }

    public void add(int x) {
        if (size == data.length) {
            grow();
        }

        data[size] = x;
        size++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == data.length) {
            grow();
        }

        for (int i = size; i > index; i--) {
            metrics.addStep();
            metrics.addMove();

            data[i] = data[i - 1];
        }

        data[index] = x;
        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        metrics.addStep();
        int removed = data[index];

        for (int i = index; i < size - 1; i++) {
            metrics.addStep();
            metrics.addMove();

            data[i] = data[i + 1];
        }

        size--;
        return removed;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        metrics.addStep();
        return data[index];
    }

    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            metrics.addStep();
            metrics.addComparison();

            if (data[i] == x) {
                return true;
            }
        }

        return false;
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
        int[] newData = new int[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            metrics.addStep();
            metrics.addMove();

            newData[i] = data[i];
        }

        data = newData;
    }
}