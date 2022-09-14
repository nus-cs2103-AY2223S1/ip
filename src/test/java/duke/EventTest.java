package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Event class and its methods.
 *
 * @author Yuvaraj Kumaresan
 */
public class EventTest {

    /**
     * Test event.
     */
    Event testEventOne = new Event("A test", "test time");

    /**
     * Tests if the toString method is implemented correctly.
     */
    @Test
    public void testToString() {
        assertEquals("[E][ ] A test (at: test time)", testEventOne.toString());
    }

    /**
     * Tests if the getAt method is implemented correctly/
     */
    @Test
    public void testEventGetter() {
        assertEquals("test time", testEventOne.getAt());
    }

}