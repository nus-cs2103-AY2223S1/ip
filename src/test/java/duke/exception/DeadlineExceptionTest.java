package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineExceptionTest {
    @Test
    public void createDeadlineException_defaultInput_getCorrectErrorMessage() {
        DeadlineException sampleException = new DeadlineException();
        assertEquals(sampleException.getMessage(), "☹ OOPS!!! "
                + "The description and time limit of deadline cannot be empty.");
    }

    @Test
    public void createDeadlineException_customInput_getCorrectErrorMessage() {
        DeadlineException sampleException = new DeadlineException("Invalid deadline.");
        assertEquals(sampleException.getMessage(),
                "☹ OOPS!!! Invalid deadline.");
    }
}
