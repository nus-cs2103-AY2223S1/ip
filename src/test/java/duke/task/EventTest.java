package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeInvalidTimeException;


public class EventTest {
    @Test
    public void firstConstructor_normalInput() {
        try {
            Event event = new Event("Test", "2020-09-12 1330");
            assertEquals("[E][ ] Test (at: Sep 12 2020 1:30pm)", event.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void firstConstructor_noonInput() {
        try {
            Event event = new Event("Test", "2020-09-12 1200");
            assertEquals("[E][ ] Test (at: Sep 12 2020 12pm)", event.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void firstConstructor_midnightInput() {
        try {
            Event event = new Event("Test", "2020-09-12 0000");
            assertEquals("[E][ ] Test (at: Sep 12 2020 12am)", event.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void firstConstructor_invalidTimeInput_exceptionThrown() {
        try {
            Event event = new Event("Test", "2020-09-12 12000");
            fail();
        } catch (DukeInvalidTimeException e) {
            DukeInvalidTimeException exception = new DukeInvalidTimeException();
            assertEquals(exception.getMessage(), e.getMessage());
        }
    }

    @Test
    public void firstConstructor_invalidDateInput_exceptionThrown() {
        try {
            Event event = new Event("Test", "2020-09-123 1200");
            fail();
        } catch (DukeInvalidTimeException e) {
            DukeInvalidTimeException exception = new DukeInvalidTimeException();
            assertEquals(exception.getMessage(), e.getMessage());
        }
    }

    @Test
    public void secondConstructor_notDoneInput() {
        try {
            Event event = new Event("Test", "0", "2020-09-12 1330");
            assertEquals("[E][ ] Test (at: Sep 12 2020 1:30pm)", event.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void secondConstructor_doneInput() {
        try {
            Event event = new Event("Test", "1", "2020-09-12 1330");
            assertEquals("[E][X] Test (at: Sep 12 2020 1:30pm)", event.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void markEvent_unmarkedEvent() {
        try {
            Event event = new Event("Test", "2020-09-12 1330");
            event.markAsDone();
            assertEquals("[E][X] Test (at: Sep 12 2020 1:30pm)", event.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void unmarkEvent_markedEvent() {
        try {
            Event event = new Event("Test", "1", "2020-09-12 1330");
            event.markAsUndone();
            assertEquals("[E][ ] Test (at: Sep 12 2020 1:30pm)", event.toString());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getDescription_normalInput() {
        try {
            Event event = new Event("Test", "0", "2020-09-12 1330");
            assertEquals("Test", event.getDescription());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getType_normalInput() {
        try {
            Event event = new Event("Test", "0", "2020-09-12 1330");
            assertEquals("E", event.getType());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getDate_normalInput() {
        try {
            Event event = new Event("Test", "0", "2020-09-12 1330");
            assertEquals("2020-09-12 1330", event.getDate());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getDateTime_normalInput() {
        try {
            Event event = new Event("Test", "0", "2020-09-12 1330");
            assertEquals("Sep 12 2020 1:30pm", event.getDateTime());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void getDone_notDoneInput() {
        try {
            Event event = new Event("Test", "0", "2020-09-12 1330");
            assertEquals(false, event.getIsDone());
        } catch (DukeInvalidTimeException e) {
            fail();
        }
    }
}
