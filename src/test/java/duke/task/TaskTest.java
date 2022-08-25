package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void unmark_unmarkedTask_Error() {
        Task task = new Task("Test task");
        try {
            task.unmarkIsDone();
        } catch (DukeException e) {
            assertEquals("Task is not done", e.getMessage());
        }
    }

    @Test
    public void mark_markedTask_Error() {
        Task task = new Task("Test task", true);
        try {
            task.markIsDone();
        } catch (DukeException e) {
            assertEquals("Task is already done", e.getMessage());
        }
    }

    @Test
    public void print_saveString() {
        Task task = new Task("Test task", true);
        assertEquals(task.toSaveString(), "1|Test task");
    }
}
