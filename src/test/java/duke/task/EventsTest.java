package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventsTest {
    @Test
    @DisplayName("Check String Output of Event Object")
    public void checkStringOutputOfEventObject() {
        String output = null;
        try {
            output = new Events("read book", "10/10/2000 1000", false).toString();
        } catch (DukeException e) {
            fail(e.getMessage());
        }
        assertEquals("[E][ ] read book (at: Oct 10 2000 1000)", output);
    }

    @Test
    @DisplayName("Check Save String Output of Event Object")
    public void checkSaveStringOutputOfEventObject() {
        String output = null;
        try {
            output = new Events("read book", "10/10/2000 1000", false).toSaveString();
        } catch (DukeException e) {
            fail(e.getMessage());
        }
        assertEquals("event 0 read book 10/10/2000 1000", output);
    }
}
