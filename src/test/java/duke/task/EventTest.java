package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class EventTest {

    @Test
    public void toString_newEvent_returnsString() throws DukeException {
        Event event = new Event("Test", "2022-09-21");
        assertEquals("[E][ ] Test (at: Sep 21 2022)", event.toString());
    }

    @Test
    public void toString_invalidDate_exceptionThrown() {
        try {
            Event event = new Event("Test", "21-09-2022");
            assertEquals("[E][ ] Test (at: Sep 21 2022)", event.toString());
            fail();
        } catch (Exception e) {
            assertEquals("Date should be in yyyy-mm-dd format.", e.getMessage());
        }
    }

    @Test
    public void toStorageString_newEvent_returnsStorageString() throws DukeException {
        Event event = new Event("Test", "2022-09-21");
        assertEquals("E | 0 | Test | 2022-09-21", event.toStorageString());
    }
}
