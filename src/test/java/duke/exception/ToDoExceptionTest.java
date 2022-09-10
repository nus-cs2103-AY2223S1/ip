package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoExceptionTest {
    @Test
    public void createToDoException_getCorrectErrorMessage() {
        ToDoException sampleException = new ToDoException();
        assertEquals(sampleException.getMessage(), "☹ OOPS!!! "
                + "The description of a todo cannot be empty.");
    }
}
