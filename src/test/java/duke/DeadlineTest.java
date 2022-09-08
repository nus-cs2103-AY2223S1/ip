package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;

public class DeadlineTest {

    @Test
    public void toString_newDeadline_success() {
        Deadline deadline = new Deadline("test1", "2022-01-01");
        assertEquals("[D][ ] test1 (by: Jan 01 2022)", deadline.toString());
    }

    @Test
    public void newDeadline_invalidDate_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("test1", "01-01-2022");
            assertEquals("[D][ ] test1 (by: Jan 01 2022)", deadline.toString());
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! You need to input in yyyy-mm-dd format!", e.getMessage());
        }
    }

    @Test
    public void toStorage_newDeadline_success() {
        Deadline deadline = new Deadline("test1", "2022-01-01");
        assertEquals("D | 0 | test1 | 2022-01-01", deadline.toStorage());
    }
}
