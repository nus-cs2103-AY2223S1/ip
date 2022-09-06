package isara;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import isara.task.Event;
import isara.task.Task;

public class EventTest {
    @Test
    public void testMarkFunctionalityForEvent() {
        Task event = new Event("Concert", "Buona Vista");
        assertEquals(event.toString(), "[E][ ] Concert (at: Buona Vista)");
        event.mark();
        assertEquals(event.toString(), "[E][X] Concert (at: Buona Vista)");
    }

    @Test
    public void testUnmarkFunctionalityForEvent() {
        Task event = new Event("Concert", "Buona Vista");
        event.mark();
        assertEquals(event.toString(), "[E][X] Concert (at: Buona Vista)");
        event.unmark();
        assertEquals(event.toString(), "[E][ ] Concert (at: Buona Vista)");
    }

    @Test
    public void testGetStatusIconForEvent() {
        Task event = new Event("Concert", "Buona Vista");
        event.mark();
        assertEquals(event.toString(), "[E][X] Concert (at: Buona Vista)");
        assertEquals(event.getStatusIcon(), "X");
        event.unmark();
        assertEquals(event.toString(), "[E][ ] Concert (at: Buona Vista)");
        assertEquals(event.getStatusIcon(), " ");
    }
}
