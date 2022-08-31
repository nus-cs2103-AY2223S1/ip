package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;



public class EventTest {
    @Test
    public void instantiate_invalidDate_exceptionThrown() {
        try {
            Event event = new Event("Party", "23-10-2022");
            assertEquals("[E][ ] Party (at: Oct 23 2022)", event.toString());
            fail();
        } catch (Exception e) {
            assertEquals("Oops!!! Date should be in yyyy-mm-dd format.", e.getMessage());
        }
    }

    @Test
    public void instantiate_validDate_returnsCorrectString() throws DukeException {
        Event event = new Event("Party", "2022-10-23");
        assertEquals("[E][ ] Party (at: Oct 23 2022)", event.toString());
    }

    @Test
    public void toStorageString_validDate_returnsCorrectStorageString() throws DukeException {
        Event event = new Event("Party", "2022-10-23");
        assertEquals("E | 0 | Party | 2022-10-23", event.toStorageString());
    }
}
