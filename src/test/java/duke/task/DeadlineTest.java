package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DeadlineTest {

    @Test
    public void toString_newDeadline_returnsString() throws DukeException {
        Deadline deadline = new Deadline("Test", "2022-09-23");
        assertEquals("[D][ ] Test (by: Sep 23 2022)", deadline.toString());
    }

    @Test
    public void toString_invalidDate_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("Test", "23-09-2022");
            assertEquals("[D][ ] Test (by: Sep 23 2022)", deadline.toString());
            fail();
        } catch (Exception e) {
            assertEquals("Date should be in yyyy-mm-dd format.", e.getMessage());
        }
    }

    @Test
    public void toStorageString_newDeadline_returnsStorageString() throws DukeException {
        Deadline deadline = new Deadline("Test", "2022-09-23");
        assertEquals("D | 0 | Test | 2022-09-23", deadline.toStorageString());
    }
}
