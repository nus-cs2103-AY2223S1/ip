import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void eventTest() {
        assertEquals(new Event("Tutorial", "2022-08-25").toString(),
                "[E][ ] Tutorial (at: Aug 25 2022)");
    }

    @Test
    public void markDeadlineTest() {
        Event event = new Event("Tutorial", "2022-08-25");
        event.markAsDone();
        assertEquals(event.toString(), "[E][X] Tutorial (at: Aug 25 2022)");
    }

    @Test
    public void unmarkDeadlineTest() {
        Event event = new Event("Tutorial", "2022-08-25");
        event.markAsDone();
        event.markAsUndone();
        assertEquals(event.toString(), "[E][ ] Tutorial (at: Aug 25 2022)");
    }
}
