package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        Event event = new Event("test", "2022-08-24");
        assertEquals("[E][ ] test (by: 24 Aug 2022)", event.toString());
    }

    @Test
    public void getType() {
        Event event = new Event("test", "2022-08-24");
        assertEquals("E", event.getType());
    }

    @Test
    public void getDate() {
        Event event = new Event("test", "2022-08-24");
        assertEquals("2022-08-24", event.getDate());
    }
}
