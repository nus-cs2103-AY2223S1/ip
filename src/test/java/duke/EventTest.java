package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event e = new Event("event project meeting ", "Mon 2-4pm");
        assertEquals("[E][ ] event project meeting (at: Mon 2-4pm)", e.toString());
    }
}
