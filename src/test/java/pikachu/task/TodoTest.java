package pikachu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a test to test to-do class objects. A <code>TodoTest</code> object corresponds to
 * a test for to-do class objects.
 */
public class TodoTest {

    /**
     * Tests the correctness of name of to-do tasks.
     */
    @Test
    public void getName_rightName() {
        assertEquals("T", new Todo("").getName());
    }

    /**
     * Tests the correctness of timing of to-do tasks, which is no timing indicated at all.
     */
    @Test
    public void getTiming_rightNoTime() {
        assertEquals("", new Todo("").getTiming());
    }
}
