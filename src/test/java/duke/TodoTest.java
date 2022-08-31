package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * TodoTest class to test Todo object methods.
 */
public class TodoTest {

    /**
     * Tests for string representation of Todo object.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] homework", new Todo("homework").toString());
    }
}
