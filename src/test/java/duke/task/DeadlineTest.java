package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {

    private static final String NO_ENDTIME_MESSAGE = "A Deadline-type Task must be provided with an ending time."
            + " Use the /by parameter to add a deadline.";
    private static final String INVALID_DATE_MESSAGE = "Please provide me a valid date in the following format:\n"
            + "YYYY-MM-DD\ni.e. 29th February 2000 is 2000-02-29";

    @Test
    public void newDeadline_unmarked_success() {
        try {
            Deadline deadline = new Deadline("UwU", "1970-01-01");
            assertEquals("[D][ ] UwU (by: Thursday, 01 January 1970)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void newDeadline_marked_success() {
        try {
            Deadline deadline = new Deadline("UwU", "1970-01-01", true);
            assertEquals("[D][✔] UwU (by: Thursday, 01 January 1970)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void newDeadline_emptyDesc_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("", "1970-01-01", true);
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a Task must not be empty.", e.getMessage());
        }
    }

    @Test
    public void newDeadline_nullValueDesc_exceptionThrown() {
        try {
            Deadline deadline = new Deadline(null, "1970-01-01", true);
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a Task must not be empty.", e.getMessage());
        }
    }

    @Test
    public void newDeadline_invalidDate_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("UwU", "01 Jan 1970");
            fail();
        } catch (DukeException e) {
            assertEquals(INVALID_DATE_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void newDeadline_invalidDate2_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("UwU", "1900-02-29");
            fail();
        } catch (DukeException e) {
            assertEquals(INVALID_DATE_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void newDeadline_emptyDate_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("UwU", "");
            fail();
        } catch (DukeException e) {
            assertEquals(NO_ENDTIME_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void newDeadline_nullValueDate_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("UwU", null);
            fail();
        } catch (DukeException e) {
            assertEquals(NO_ENDTIME_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void toStorageString_unmarked_success() {
        try {
            Deadline deadline = new Deadline("UwU", "1970-01-01");
            assertEquals("UwU / false / D / 1970-01-01", deadline.toStorageString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toStorageString_marked_success() {
        try {
            Deadline deadline = new Deadline("UwU", "1970-10-10", true);
            assertEquals("UwU / true / D / 1970-10-10", deadline.toStorageString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void setDone_setToTrue_success() {
        try {
            Deadline deadline = new Deadline("UwU", "1970-10-10");
            assertEquals(false, deadline.getIsDone());
            deadline.setDone(true);
            assertEquals(true, deadline.getIsDone());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void setDone_setToFalse_success() {
        try {
            Deadline deadline = new Deadline("UwU", "1970-10-10", true);
            assertEquals(true, deadline.getIsDone());
            deadline.setDone(false);
            assertEquals(false, deadline.getIsDone());
        } catch (DukeException e) {
            fail();
        }
    }
}
