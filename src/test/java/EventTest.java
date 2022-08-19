import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void addEventTest() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals("[E][ ] Tutorial 1 (at: Aug 25 2022)", event.toString());
    }

    @Test
    public void markEventTest() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        event.mark();
        assertEquals("[E][X] Tutorial 1 (at: Aug 25 2022)", event.toString());
    }

    @Test
    public void unmarkEventTest() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        event.mark();
        event.unmark();
        assertEquals("[E][ ] Tutorial 1 (at: Aug 25 2022)", event.toString());
    }

    @Test
    public void saveEventTest() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals("E | 0 | Tutorial 1 | 2022-08-25", event.saveTask());
    }

    @Test
    public void saveEventTest2() {
        Event event = new Event("Tutorial 1", LocalDate.parse("2022-08-25"));
        event.mark();
        assertEquals("E | 1 | Tutorial 1 | 2022-08-25", event.saveTask());
    }
}
