package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeInvalidTimeException;

public class DeadlineTest {
    @Test
    public void firstConstructor_normalInput() {
        try {
            Deadline deadline = new Deadline("Test", "2020-09-12 1330");
            assertEquals("[D][ ] Test (by: Sep 12 2020 1:30pm)", deadline.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void firstConstructor_noonInput() {
        try {
            Deadline deadline = new Deadline("Test", "2020-09-12 1200");
            assertEquals("[D][ ] Test (by: Sep 12 2020 12pm)", deadline.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void firstConstructor_midnightInput() {
        try {
            Deadline deadline = new Deadline("Test", "2020-09-12 0000");
            assertEquals("[D][ ] Test (by: Sep 12 2020 12am)", deadline.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void firstConstructor_invalidTimeInput_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("Test", "2020-09-12 12000");
            fail();
        } catch (DukeInvalidTimeException e) {
            DukeInvalidTimeException exception = new DukeInvalidTimeException();
            assertEquals(exception.getMessage(), e.getMessage());
        }
    }

    @Test
    public void firstConstructor_invalidDateInput_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("Test", "2020-09-123 1200");
            fail();
        } catch (DukeInvalidTimeException e) {
            DukeInvalidTimeException exception = new DukeInvalidTimeException();
            assertEquals(exception.getMessage(), e.getMessage());
        }
    }

    @Test
    public void secondConstructor_notDoneInput() {
        try {
            Deadline deadline = new Deadline("Test", "0", "2020-09-12 1330");
            assertEquals("[D][ ] Test (by: Sep 12 2020 1:30pm)", deadline.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void secondConstructor_doneInput() {
        try {
            Deadline deadline = new Deadline("Test", "1", "2020-09-12 1330");
            assertEquals("[D][X] Test (by: Sep 12 2020 1:30pm)", deadline.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void markDeadline_unmarkedDeadline() {
        try {
            Deadline deadline = new Deadline("Test", "2020-09-12 1330");
            deadline.markAsDone();
            assertEquals("[D][X] Test (by: Sep 12 2020 1:30pm)", deadline.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void unmarkDeadline_markedDeadline() {
        try {
            Deadline deadline = new Deadline("Test", "1", "2020-09-12 1330");
            deadline.markAsUndone();
            assertEquals("[D][ ] Test (by: Sep 12 2020 1:30pm)", deadline.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getDescription_normalInput() {
        try {
            Deadline deadline = new Deadline("Test", "0", "2020-09-12 1330");
            assertEquals("Test", deadline.getDescription());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getType_normalInput() {
        try {
            Deadline deadline = new Deadline("Test", "0", "2020-09-12 1330");
            assertEquals("D", deadline.getType());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getDate_normalInput() {
        try {
            Deadline deadline = new Deadline("Test", "0", "2020-09-12 1330");
            assertEquals("2020-09-12 1330", deadline.getDate());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getDateTime_normalInput() {
        try {
            Deadline deadline = new Deadline("Test", "0", "2020-09-12 1330");
            assertEquals("Sep 12 2020 1:30pm", deadline.getDateTime());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getDone_notDoneInput() {
        try {
            Deadline deadline = new Deadline("Test", "0", "2020-09-12 1330");
            assertEquals(false, deadline.getIsDone());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }
}
