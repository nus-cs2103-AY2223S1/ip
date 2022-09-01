package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("testing");
        assertEquals("[E][ ] testing (at: null)", event.toString());
    }

    @Test
    public void testSetDay() {
        Event event = new Event("testing");
        event.setDay(new FormatDate("2022-08-26"));
        assertEquals("Aug 26 2022", event.day.toString());
    }
}