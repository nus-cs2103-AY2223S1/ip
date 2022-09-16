package bro.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * TodoTest class.
 */
public class TodoTest {
    /**
     * Tests whether it returns the right output for todo task.
     */
    @Test
    public void toString_inputString_returnsString() {
        assertEquals("[T][ ] dinner",
                new Todo("dinner").toString());
    }
}
