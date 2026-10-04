package structures;

import metrics.OperationMetrics;

public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;
    private final OperationMetrics metrics;

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
        metrics = new OperationMetrics();
    }

    public void add(int x) {
        Node newNode = new Node(x);

        if (head == null) {
            head = newNode;
            tail = newNode;
            metrics.addMove();
            metrics.addMove();
        } else {
            tail.next = newNode;
            tail = newNode;
            metrics.addMove();
            metrics.addMove();
        }

        size++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            add(x);
            return;
        }

        Node newNode = new Node(x);

        if (index == 0) {
            newNode.next = head;
            head = newNode;

            metrics.addMove();
            metrics.addMove();
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
                metrics.addStep();
            }

            newNode.next = current.next;
            current.next = newNode;

            metrics.addMove();
            metrics.addMove();
        }

        size++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int removed;

        if (index == 0) {
            removed = head.value;
            metrics.addStep();

            head = head.next;
            metrics.addMove();

            if (size == 1) {
                tail = null;
                metrics.addMove();
            }
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
                metrics.addStep();
            }

            removed = current.next.value;
            metrics.addStep();

            if (current.next == tail) {
                tail = current;
                metrics.addMove();
            }

            current.next = current.next.next;
            metrics.addMove();
        }

        size--;
        return removed;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
            metrics.addStep();
        }

        metrics.addStep();
        return current.value;
    }

    public boolean contains(int x) {
        Node current = head;

        while (current != null) {
            metrics.addStep();
            metrics.addComparison();

            if (current.value == x) {
                return true;
            }

            current = current.next;
            metrics.addStep();
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
}