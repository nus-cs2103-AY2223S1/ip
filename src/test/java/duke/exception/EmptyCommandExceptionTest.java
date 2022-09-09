package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmptyCommandExceptionTest {
    @Test
    public void createEmptyCommandException_getCorrectErrorMessage() {
        EmptyCommandException sampleException = new EmptyCommandException();
        assertEquals(sampleException.getMessage(),
                "☹ OOPS!!! The command cannot be empty.");
    }
}
