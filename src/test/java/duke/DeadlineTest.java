package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void instantiate_exceptionThrown() {
        try {
            new Deadline("Feed cat", "Invalid entry");
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Cannot parse date. Enter date according to example, 02-12-2019 1800", e.getMessage());
        }
    }

    @Test
    public void instantiate_success() throws DukeException {
        Deadline task = new Deadline("", "02-12-2019 1800");
        assertEquals("[D][ ]  (by: Dec 02 2019)", task.toString());
    }

    @Test
    public void toString_success() throws DukeException {
        Deadline task = new Deadline("Feed cat", "02-12-2019 1800");
        assertEquals("[D][ ] Feed cat (by: Dec 02 2019)", task.toString());
    }

    @Test
    public void toFileString_success() throws DukeException {
        Deadline task = new Deadline("Feed cat", "02-12-2019 1800");
        assertEquals("D|0|Feed cat|02-12-2019 1800", task.toFileString());
    }

    @Test
    public void setTaskStatus_success() throws DukeException {
        Deadline task = new Deadline("Feed cat", "02-12-2019 1800");
        assertEquals("[D][ ] Feed cat (by: Dec 02 2019)", task.toString());
        task.setTaskStatus(true);
        assertEquals("[D][X] Feed cat (by: Dec 02 2019)", task.toString());
    }
}
