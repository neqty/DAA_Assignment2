package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayTest {

    @Test
    void testAddAndGet() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    void testAddAtIndex() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(30);
        array.add(1, 20);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
    }

    @Test
    void testRemove() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(30);

        int removed = array.remove(1);

        assertEquals(20, removed);
        assertEquals(2, array.size());
        assertEquals(30, array.get(1));
    }

    @Test
    void testContains() {
        DynamicArray array = new DynamicArray();

        array.add(5);
        array.add(10);
        array.add(15);

        assertTrue(array.contains(10));
        assertFalse(array.contains(100));
    }

    @Test
    void testDuplicateValues() {
        DynamicArray array = new DynamicArray();

        array.add(5);
        array.add(5);

        assertTrue(array.contains(5));
        assertEquals(2, array.size());
    }

    @Test
    void testFirstAndLastIndex() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(10, array.get(0));
        assertEquals(30, array.get(2));
    }

    @Test
    void testInvalidGet() {
        DynamicArray array = new DynamicArray();

        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(-1));
    }

    @Test
    void testInvalidRemove() {
        DynamicArray array = new DynamicArray();

        array.add(10);

        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(1));
    }

    @Test
    void testGrow() {
        DynamicArray array = new DynamicArray();

        for (int i = 0; i < 20; i++) {
            array.add(i);
        }

        assertEquals(20, array.size());
        assertEquals(19, array.get(19));
    }
}