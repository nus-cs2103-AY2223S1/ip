package john.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventDateTest() {
        Event event = new Event("Event", "23/8/2022");
        assertEquals("[E][ ] Event (at: Aug 23 2022)", event.toString());
        assertEquals("E | 0 | Event | 23/8/2022", event.toStorageFormat());
    }

    @Test
    public void eventDateTimeTest() {
        Event event = new Event("Event", "16/11/2000 2359");
        assertEquals("[E][ ] Event (at: Nov 16 2000 11:59 PM)", event.toString());
        assertEquals("E | 0 | Event | 16/11/2000 2359", event.toStorageFormat());
    }


    @Test
    public void markEventTest() {
        Event event = new Event("Mark event", "20/10/2012 1159");
        event.markAsDone();
        assertEquals("[E][X] Mark event (at: Oct 20 2012 11:59 AM)", event.toString());
        assertEquals("E | 1 | Mark event | 20/10/2012 1159", event.toStorageFormat());
    }

    @Test
    public void unmarkEventTest() {
        Event event = new Event("Unmark event", "20/10/2012 1159");
        event.markAsUndone();
        assertEquals("[E][ ] Unmark event (at: Oct 20 2012 11:59 AM)", event.toString());
        assertEquals("E | 0 | Unmark event | 20/10/2012 1159", event.toStorageFormat());
    }

    @Test
    public void isEqualDateEventTest() {
        Event event = new Event("Equal event", "20/10/2012 1159");
        assertTrue(event.isEqualDate(LocalDate.parse("2012-10-20")));
        assertFalse(event.isEqualDate(LocalDate.parse("2012-10-21")));
    }

    @Test
    public void isMatchingKeywordsEventTest() {
        Event event = new Event("Matching Keywords Event", "20/10/2012 1159");
        assertTrue(event.isMatchingKeyword("Even"));
        assertFalse(event.isMatchingKeyword("Odd"));
    }
}
