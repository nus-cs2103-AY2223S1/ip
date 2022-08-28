package unc.task;

import org.junit.jupiter.api.Test;
import unc.UncException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    public static Event testEvent () {
        Event temp = null;
        try {
            temp = new Event("Celebrate", "2001-09-11");
        } catch (UncException uncException) {
            System.out.println("Error occurred in setup.");
        }
        return temp;
    }

    @Test
    public void toStringTest() {
        assertEquals("[E][ ] Celebrate (at: 11-09-2001)",testEvent().toString());
    }

    @Test
    public void markEventTest() {
        Event event = testEvent();
        event.markAsDone();
        assertEquals("[E][X] Celebrate (at: 11-09-2001)", event.toString());
    }

    @Test
    public void unmarkEventTest() {
        Event event = testEvent();
        event.markAsDone();
        event.markAsNotDone();
        assertEquals("[E][ ] Celebrate (at: 11-09-2001)", event.toString());
    }

    @Test
    public void saveEventTest() {
        Event event = testEvent();
        assertEquals("E///Celebrate///2001-09-11///false", event.toStorageString());
    }
}
