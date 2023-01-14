package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addTask_incompleteInput_exceptionThrown() {
        try {
            new TaskList().addTask("", "/");
            fail();
        } catch (DukeException e) {
            assertEquals("oops, the description of your task seems to be incomplete!", e.getMessage());
        }
    }
}
