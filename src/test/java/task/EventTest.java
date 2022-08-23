package task;

import jduke.task.Event;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    @Test
    public void EventDateTest() {
        Event Event = new Event("Event", "23/8/2022");
        assertEquals("[E][ ] Event (at: Aug 23 2022)", Event.toString());
        assertEquals("E | 0 | Event | 23/8/2022", Event.toStorageFormat());
    }

    @Test
    public void EventDateTimeTest() {
        Event Event = new Event("Event", "16/11/2000 2359");
        assertEquals("[E][ ] Event (at: Nov 16 2000 11:59 PM)", Event.toString());
        assertEquals("E | 0 | Event | 16/11/2000 2359", Event.toStorageFormat());
    }


    @Test
    public void markEventTest() {
        Event Event = new Event("Mark Event", "20/10/2012 1159");
        Event.markAsDone();
        assertEquals("[E][X] Mark Event (at: Oct 20 2012 11:59 AM)", Event.toString());
        assertEquals("E | 1 | Mark Event | 20/10/2012 1159", Event.toStorageFormat());
    }

    @Test
    public void unmarkEventTest() {
        Event Event = new Event("Unmark Event", "20/10/2012 1159");
        Event.markAsUndone();
        assertEquals("[E][ ] Unmark Event (at: Oct 20 2012 11:59 AM)", Event.toString());
        assertEquals("E | 0 | Unmark Event | 20/10/2012 1159", Event.toStorageFormat());
    }

    @Test
    public void isEqualDateEventTest() {
        Event Event = new Event("Equal Event", "20/10/2012 1159");
        assertTrue(Event.isEqualDate(LocalDate.parse("2012-10-20")));
        assertFalse(Event.isEqualDate(LocalDate.parse("2012-10-21")));
    }

    @Test
    public void isMatchingKeywordsEventTest() {
        Event event = new Event("Matching Keywords Event", "20/10/2012 1159");
        assertTrue(event.isMatchingKeyword("Even"));
        assertFalse(event.isMatchingKeyword("Odd"));
    }
}
