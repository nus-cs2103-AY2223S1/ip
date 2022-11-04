package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {
    private static final String NO_TIMERANGE_MESSAGE = "An Event-type Task must be provided with a time range."
            + " Use the /at parameter to add a time range.";
    private static final String INVALID_DATE_MESSAGE = "Please provide me valid date(s) in the following format:\n"
            + "  YYYY1-MM1-DD1 YYYY2-MM2-DD2\n"
            + "i.e. 29th February 2000 to 2nd March 2000 is 2000-02-29 2000-03-02."
            + " You can provide only one date if you choose.";
    private static final String INVALID_DATERANGE_MESSAGE = "The second date is prior to the first date.";
    @Test
    public void newEvent_unmarked_success() {
        try {
            Event event = new Event("Test Event", "1970-01-01");
            assertEquals("[E][ ] Test Event (at: Thursday, 01 January 1970)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void newEvent_marked_success() {
        try {
            Event event = new Event("Test Event", "1970-01-01", true);
            assertEquals("[E][✔] Test Event (at: Thursday, 01 January 1970)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void newEvent_twoDates_success() {
        try {
            Event event = new Event("Test Event", "1970-01-01 1970-01-04", false);
            assertEquals("[E][ ] Test Event (at: Thursday, 01 January 1970 - Sunday, 04 January 1970)",
                    event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void newEvent_emptyDesc_exceptionThrown() {
        try {
            Event event = new Event("", "1970-01-01", true);
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a Task must not be empty.", e.getMessage());
        }
    }

    @Test
    public void newEvent_nullValueDesc_exceptionThrown() {
        try {
            Event event = new Event(null, "1970-01-01", true);
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a Task must not be empty.", e.getMessage());
        }
    }

    @Test
    public void newEvent_invalidDate_exceptionThrown() {
        try {
            Event event = new Event("UwU", "01 Jan 1970");
            fail();
        } catch (DukeException e) {
            assertEquals(INVALID_DATE_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void newEvent_invalidDate2_exceptionThrown() {
        try {
            Event event = new Event("UwU", "1900-02-29");
            fail();
        } catch (DukeException e) {
            assertEquals(INVALID_DATE_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void newEvent_invalidDate3_exceptionThrown() {
        try {
            Event event = new Event("UwU", "2000-02-29 01 Jan 2003");
            fail();
        } catch (DukeException e) {
            assertEquals(INVALID_DATE_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void newEvent_invalidDateRange_exceptionThrown() {
        try {
            Event event = new Event("UwU", "2022-08-24 2022-08-21");
            fail();
        } catch (DukeException e) {
            assertEquals(INVALID_DATERANGE_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void newEvent_emptyDate_exceptionThrown() {
        try {
            Event event = new Event("UwU", "");
            fail();
        } catch (DukeException e) {
            assertEquals(NO_TIMERANGE_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void newDeadline_nullValueDate_exceptionThrown() {
        try {
            Event event = new Event("UwU", null);
            fail();
        } catch (DukeException e) {
            assertEquals(NO_TIMERANGE_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void toStorageString_unmarked_success() {
        try {
            Event event = new Event("UwU", "1970-01-01");
            assertEquals("UwU / false / E / 1970-01-01 1970-01-01", event.toStorageString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toStorageString_marked_success() {
        try {
            Event event = new Event("UwU", "1970-10-10", true);
            assertEquals("UwU / true / E / 1970-10-10 1970-10-10", event.toStorageString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void toStorageString_twoDates_success() {
        try {
            Event event = new Event("UwU", "1970-10-10 2000-01-01");
            assertEquals("UwU / false / E / 1970-10-10 2000-01-01", event.toStorageString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void setDone_setToTrue_success() {
        try {
            Event event = new Event("UwU", "1970-10-10");
            assertEquals(false, event.getIsDone());
            event.setDone(true);
            assertEquals(true, event.getIsDone());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void setDone_setToFalse_success() {
        try {
            Event event = new Event("UwU", "1970-10-10", true);
            assertEquals(true, event.getIsDone());
            event.setDone(false);
            assertEquals(false, event.getIsDone());
        } catch (DukeException e) {
            fail();
        }
    }
}
