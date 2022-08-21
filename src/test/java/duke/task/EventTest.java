package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void addToDoTest() {
        Event event = new Event("event die /at 2018-08-09");
        assertEquals("[E][ ] die (at: Aug 09 2018)", event.toString());
    }

    @Test
    public void markToDoTest() {
        Event event = new Event("event die /at 2018-08-09");
        event.markAsDone();
        assertEquals("[E][X] die (at: Aug 09 2018)", event.toString());
    }
}
