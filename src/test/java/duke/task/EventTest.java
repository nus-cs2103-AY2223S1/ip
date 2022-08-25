package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void addEventTest() {
        Event event = new Event("Book Meet", LocalDate.parse("2069-06-09"));
        assertEquals("[E][ ] Book Meet (at: Jun 9 2069)", event.toString());
    }

    @Test
    public void markEventTest() {
        Event event = new Event("Book Meet", LocalDate.parse("2069-06-09"));
        event.markAsDone();
        assertEquals("[E][X] Book Meet (at: Jun 9 2069)", event.toString());
    }

    @Test
    public void unmarkEventTest() {
        Event event = new Event("Book Meet", LocalDate.parse("2069-06-09"));
        event.markAsDone();
        event.markAsUndone();
        assertEquals("[E][ ] Book Meet (at: Jun 9 2069)", event.toString());
    }

    @Test
    public void saveEventTest() {
        Event event = new Event("Book Meet", LocalDate.parse("2069-06-09"));
        assertEquals("E | 0 | Book Meet | 2069-06-09", event.save());
    }

    @Test
    public void getTimeTest() {
        Event event = new Event("Book Meet", LocalDate.parse("2069-06-09"));
        assertEquals(LocalDate.parse("2069-06-09"), event.getTime());
    }
}
