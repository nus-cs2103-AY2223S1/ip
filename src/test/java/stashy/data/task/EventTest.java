package stashy.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Test class for Event.
 */
public class EventTest {
    @Test
    public void toString_undoneEvent_success() {
        LocalDateTime atDateTime = LocalDateTime.of(2022, 8, 10, 12, 55);
        Event event = new Event("Go to bookstore", atDateTime, false);
        assertEquals("[E][ ] Go to bookstore (at: Aug 10 2022 12:55)", event.toString());
    }

    @Test
    public void toString_doneEvent_success() {
        LocalDateTime atDateTime = LocalDateTime.of(2022, 8, 10, 12, 55);
        Event event = new Event("Go to bookstore", atDateTime, true);
        assertEquals("[E][X] Go to bookstore (at: Aug 10 2022 12:55)", event.toString());
    }
}
