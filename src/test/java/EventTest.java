import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void add_event_success() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals("[E][ ] Tutorial 1 (at: Aug 25 2022)", event.toString());
    }

    @Test
    public void mark_event_success() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        event.mark();
        assertEquals("[E][X] Tutorial 1 (at: Aug 25 2022)", event.toString());
    }

    @Test
    public void unmark_event_success() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        event.mark();
        event.unmark();
        assertEquals("[E][ ] Tutorial 1 (at: Aug 25 2022)", event.toString());
    }

    @Test
    public void save_unmarkedEvent_success() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals("E | 0 | 0 | Tutorial 1 | 2022-08-25", event.saveTask());
    }

    @Test
    public void save_markedEvent_success() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        event.mark();
        assertEquals("E | 1 | 0 | Tutorial 1 | 2022-08-25", event.saveTask());
    }

    @Test
    public void setHighPriority_event_success() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        event.setHighPriority();
        assertEquals("[E][ ][!] Tutorial 1 (at: Aug 25 2022)", event.toString());
    }
}
