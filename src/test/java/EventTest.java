package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.event;

public class EventTest {
    @Test
    public void testToString() {
        event event = new event("testing");
        assertEquals("[E][ ] testing (at: null)", event.toString());
    }

    @Test
    public void testSetDay() {
        event event = new event("testing");
        event.setDay(new formatDate("2022-08-26"));
        assertEquals("Aug 26 2022", event.day.toString());
    }
}