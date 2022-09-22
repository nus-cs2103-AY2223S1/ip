import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void addTest() {
        assertEquals(new Event("project meeting", "2022-10-29").toString(),
                "[E][ ] project meeting (at: Oct 29 2022)");
    }

    @Test
    public void markTest() {
        Event event = new Event("project meeting", "2022-10-29");
        event.markTask();
        assertEquals(event.toString(),
                "[E][X] project meeting (at: Oct 29 2022)");
    }

    @Test
    public void unmarkTest() {
        Event deadline = new Event("project meeting", "2022-10-29");
        deadline.markTask();
        deadline.unmarkTask();
        assertEquals(deadline.toString(),
                "[E][ ] project meeting (at: Oct 29 2022)");
    }
}
