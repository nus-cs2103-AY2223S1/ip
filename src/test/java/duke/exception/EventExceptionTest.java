package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventExceptionTest {
    @Test
    public void createEventException_defaultInput_getCorrectErrorMessage() {
        EventException sampleException = new EventException();
        assertEquals(sampleException.getMessage(), "☹ OOPS!!! "
                + "The description and time of event cannot be empty.");
    }

    @Test
    public void createEventException_customInput_getCorrectErrorMessage() {
        EventException sampleException = new EventException("Invalid time.");
        assertEquals(sampleException.getMessage(),
                "☹ OOPS!!! Invalid time.");
    }
}
