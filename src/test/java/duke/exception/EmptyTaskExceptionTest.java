package duke.exception;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * EmptyTaskExceptionTest class to test features of the EmptyTaskException exception.
 */
public class EmptyTaskExceptionTest {

    /**
     * Tests the toString method.
     */
    @Test
    public void emptyTaskToStringTest() {
        assertEquals(new EmptyTaskException("deadline").toString()
                , "  Ono!! D: The task deadline is empty! Please add the task description:)");
    }
}
