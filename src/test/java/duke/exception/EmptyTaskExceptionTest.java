package duke.exception;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyTaskExceptionTest {

    @Test
    public void emptyTaskToStringTest() {
        assertEquals(new EmptyTaskException("deadline").toString()
                , "  Ono!! D: The task deadline is empty! Please add the task description:)");
    }
}
