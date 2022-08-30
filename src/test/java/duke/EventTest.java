package duke;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void instantiate_exceptionThrown() {
        try {
            new Event("Feed cat", "Invalid entry");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Cannot parse date. Enter date according to example, 02-12-2019 1800", e.getMessage());
        }
    }

    @Test
    public void instantiate_success() throws DukeException {
        Event task = new Event("", "02-12-2019 1800");
        assertEquals("[E][ ]  (at: Dec 02 2019)", task.toString());
    }

    @Test
    public void toString_success() throws DukeException {
        Event task = new Event("Feed cat", "02-12-2019 1800");
        assertEquals("[E][ ] Feed cat (at: Dec 02 2019)", task.toString());
    }

    @Test
    public void toFileString_success() throws DukeException {
        Event task = new Event("Feed cat", "02-12-2019 1800");
        assertEquals("E|0|Feed cat|02-12-2019 1800", task.toFileString());
    }

    @Test
    public void setTaskStatus_success() throws DukeException {
        Event task = new Event("Feed cat", "02-12-2019 1800");
        assertEquals("[E][ ] Feed cat (at: Dec 02 2019)", task.toString());
        task.setTaskStatus(true);
        assertEquals("[E][X] Feed cat (at: Dec 02 2019)", task.toString());
    }
}
