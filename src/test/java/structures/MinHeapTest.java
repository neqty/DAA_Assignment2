package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    void testInsertAndPeek() {
        MinHeap heap = new MinHeap();

        heap.insert(30);
        heap.insert(10);
        heap.insert(20);

        assertEquals(10, heap.peekMin());
    }

    @Test
    void testExtractMin() {
        MinHeap heap = new MinHeap();

        heap.insert(30);
        heap.insert(10);
        heap.insert(20);

        assertEquals(10, heap.extractMin());
        assertEquals(20, heap.peekMin());
    }

    @Test
    void testSortedOutput() {
        MinHeap heap = new MinHeap();

        heap.insert(50);
        heap.insert(20);
        heap.insert(40);
        heap.insert(10);
        heap.insert(30);

        assertEquals(10, heap.extractMin());
        assertEquals(20, heap.extractMin());
        assertEquals(30, heap.extractMin());
        assertEquals(40, heap.extractMin());
        assertEquals(50, heap.extractMin());
    }

    @Test
    void testDuplicates() {
        MinHeap heap = new MinHeap();

        heap.insert(5);
        heap.insert(5);
        heap.insert(10);

        assertEquals(5, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.extractMin());
    }

    @Test
    void testOneElement() {
        MinHeap heap = new MinHeap();

        heap.insert(99);

        assertEquals(99, heap.peekMin());
        assertEquals(99, heap.extractMin());
        assertEquals(0, heap.size());
    }

    @Test
    void testEmptyPeek() {
        MinHeap heap = new MinHeap();

        assertThrows(IllegalStateException.class, heap::peekMin);
    }

    @Test
    void testEmptyExtract() {
        MinHeap heap = new MinHeap();

        assertThrows(IllegalStateException.class, heap::extractMin);
    }

    @Test
    void testManyValues() {
        MinHeap heap = new MinHeap();

        for (int i = 100; i >= 1; i--) {
            heap.insert(i);
        }

        for (int i = 1; i <= 100; i++) {
            assertEquals(i, heap.extractMin());
        }
    }
}