package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;



public class DeadlineTest {

    @Test
    public void instantiate_invalidDate_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("CS2100", "23-10-2022");
            assertEquals("[D][ ] CS2100 (by: Oct 23 2022)", deadline.toString());
            fail();
        } catch (Exception e) {
            assertEquals("Oops!!! Date should be in yyyy-mm-dd format.", e.getMessage());
        }
    }

    @Test
    public void instantiate_validDate_returnsCorrectString() throws DukeException {
        Deadline deadline = new Deadline("CS2100", "2022-10-23");
        assertEquals("[D][ ] CS2100 (by: Oct 23 2022)", deadline.toString());
    }

    @Test
    public void toStorageString_validDate_returnsCorrectStorageString() throws DukeException {
        Deadline deadline = new Deadline("CS2100", "2022-10-23");
        assertEquals("D | 0 | CS2100 | 2022-10-23", deadline.toStorageString());
    }





}

