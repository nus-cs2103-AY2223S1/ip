package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toString_dummyEvent_success() {
        Event event = new Event("test", "2022-08-24");
        assertEquals("[E][ ] test (at: 24 Aug 2022)", event.toString());
    }

    @Test
    public void getType_dummyEvent_success() {
        Event event = new Event("test", "2022-08-24");
        assertEquals("E", event.getType());
    }

    @Test
    public void getDate_dummyEvent_success() {
        Event event = new Event("test", "2022-08-24");
        assertEquals("2022-08-24", event.getDate());
    }

    @Test
    public void isOnDate_sameDate_success() {
        Event event = new Event("test", "2022-08-24");
        assertTrue(event.isOnDate("2022-08-24"));
    }

    @Test
    public void isOnDate_differentDate_success() {
        Event event = new Event("test", "2022-08-24");
        assertFalse(event.isOnDate("2022-01-02"));
    }
}
