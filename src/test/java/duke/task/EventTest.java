package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.EventException;

public class EventTest {
    @Test
    public void createEvent_validInput_getCorrectStringRepresentation() {
        try {
            Event sampleEvent = new Event("winter break", "2022-12-07");
            assertEquals(sampleEvent.toString(), "[E][ ] winter break "
                    + "(at: Dec 7 2022)");
        } catch (EventException error) {
            fail("Should not have thrown any exception.");
        }
    }

    @Test
    public void createEvent_validInput_getCorrectStorageRepresentation() {
        try {
            Event sampleEvent = new Event("summer break", "2023-05-08");
            assertEquals(sampleEvent.toStorageRepresentation(), "E| |"
                    + "summer break|2023-05-08");
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
}
