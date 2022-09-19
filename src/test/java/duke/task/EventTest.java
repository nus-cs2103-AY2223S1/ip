package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.EventException;

public class EventTest {
    @Test
    public void createEvent_validInput_getCorrectStringRepresentation() {
        try {
            Event sampleEvent = new Event("winter break", "2022-12-07", "holiday");
            assertEquals(sampleEvent.toString(), "[E][ ] winter break "
                    + "(at: Dec 7 2022) [holiday]");
        } catch (EventException error) {
            fail("Should not have thrown any exception.");
        }
    }

    @Test
    public void createEvent_validInput_getCorrectStorageRepresentation() {
        try {
            Event sampleEvent = new Event("summer break", "2023-05-08", "holiday");
            assertEquals(sampleEvent.toStorageRepresentation(), "E| |"
                    + "summer break|2023-05-08#holiday");
        } catch (EventException error) {
            fail("Should not have thrown any exception.");
        }
    }

    @Test
    public void createEvent_invalidInput_throwError() {
        try {
            Event sampleEvent = new Event("party", "today");
            fail("Should have thrown EventException");
        } catch (EventException error) {
            return;
        }
    }

    @Test
    public void checkIsOnGivenDate_returnAsExpected() {
        try {
            Event sampleEvent = new Event("cs2103t meeting", "2022-09-10");
            assertTrue(sampleEvent.isOnGivenDate(LocalDate.parse("2022-09-10")));
            assertFalse(sampleEvent.isOnGivenDate(LocalDate.parse("2022-09-11")));
        } catch (EventException error) {
            fail("Should not have thrown any exception");
        }
    }
}
