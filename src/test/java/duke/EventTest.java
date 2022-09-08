package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Event;

public class EventTest {

    @Test
    public void toString_newEvent_success() {
        Event event = new Event("test1", "2022-01-01");
        assertEquals("[E][ ] test1 (at: Jan 01 2022)", event.toString());
    }

    @Test
    public void newEvent_invalidDate_exceptionThrown() {
        try {
            Event event = new Event("test1", "01-01-2022");
            assertEquals("[E][ ] test1 (at: Jan 01 2022)", event.toString());
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! You need to input in yyyy-mm-dd format!", e.getMessage());
        }
    }

    @Test
    public void toStorage_newEvent_success() {
        Event event = new Event("test1", "2022-01-01");
        assertEquals("E | 0 | test1 | 2022-01-01", event.toStorage());
    }
}
