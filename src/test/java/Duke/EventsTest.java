package Duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Events class.
 */
public class EventsTest {
    /**
     * Creation of event object.
     */
    Events event = new Events("test", false, false, "1500", 1);

    /**
     * To test getDate().
     */
    @Test
    @DisplayName("Test getDate")
    public void testGetDate() {
        assertEquals("1500", event.getDate());
    }

    /**
     * To test taskType().
     */
    @Test
    @DisplayName("Test tasktype")
    public void testTaskType() {
        assertEquals("E", event.taskType());
    }

    /**
     * To test toString().
     */
    @Test
    @DisplayName("Test toString")
    public void testToString() {
        assertEquals("[E][ ] test (at: 1500)", event.toString());
    }
}
