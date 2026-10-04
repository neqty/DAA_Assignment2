package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    void testAddAndGet() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testAddAtIndex() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(30);
        list.add(1, 20);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testRemove() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        int removed = list.remove(1);

        assertEquals(20, removed);
        assertEquals(2, list.size());
        assertEquals(30, list.get(1));
    }

    @Test
    void testRemoveFirst() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);

        assertEquals(10, list.remove(0));
        assertEquals(20, list.get(0));
    }

    @Test
    void testContains() {
        MyLinkedList list = new MyLinkedList();

        list.add(5);
        list.add(10);
        list.add(15);

        assertTrue(list.contains(10));
        assertFalse(list.contains(100));
    }

    @Test
    void testDuplicates() {
        MyLinkedList list = new MyLinkedList();

        list.add(7);
        list.add(7);

        assertEquals(2, list.size());
        assertTrue(list.contains(7));
    }

    @Test
    void testFirstAndLastIndex() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(30, list.get(2));
    }

    @Test
    void testInvalidGet() {
        MyLinkedList list = new MyLinkedList();

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void testInvalidRemove() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }
}