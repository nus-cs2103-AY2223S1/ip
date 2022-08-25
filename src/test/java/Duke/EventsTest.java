package Duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventsTest {
    Events event = new Events("test", false, false, "1500", 1);

    @Test
    @DisplayName("Test getDate")
    public void testGetDate() {
        assertEquals("1500", event.getDate());
    }

    @Test
    @DisplayName("Test tasktype")
    public void testTaskType() {
        assertEquals("E", event.taskType());
    }

    @Test
    @DisplayName("Test toString")
    public void testToString() {
        assertEquals("[E][ ] test (at: 1500)", event.toString());
    }
}
