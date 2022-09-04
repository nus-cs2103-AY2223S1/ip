package pikachu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a test to test event class objects. A <code>EventTest</code> object corresponds to
 * a test for event class objects.
 */
public class EventTest {

    /**
     * Tests the correctness of name of event tasks.
     */
    @Test
    public void getName_rightName() {
        assertEquals("E", new Event("", "").getName());
    }

    /**
     * Tests the correctness of location of event tasks.
     */
    @Test
    public void getLocation_rightLocation() {
        assertEquals("RC4", new Event("", "RC4").getTiming());
    }
}
