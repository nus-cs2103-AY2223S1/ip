package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    @Test
    public void createDukeException_defaultInput_getCorrectErrorMessage() {
        DukeException sampleException = new DukeException();
        assertEquals(sampleException.getMessage(),
                "☹ OOPS!!! I'm Sorry, Yem doesn't know what that means.");
    }

    @Test
    public void createDukeException_customInput_getCorrectErrorMessage() {
        DukeException sampleException = new DukeException("I don't understand.");
        assertEquals(sampleException.getMessage(),
                "☹ OOPS!!! I don't understand.");
    }
}
