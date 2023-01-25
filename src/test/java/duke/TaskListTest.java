package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void mark_validTaskNumber_success() throws DukeException {
        TaskList tasks = new TaskList();
        Task newTask = new ToDo("testing");
        tasks.add(newTask);
        tasks.mark(1);
        assertEquals("[T][X] testing", newTask.toString());
    }

    @Test
    public void mark_invalidTaskNumber_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            Task newTask = new ToDo("testing");
            tasks.add(newTask);
            tasks.mark(2);
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter a valid task number!", e.getMessage());
        }

    }
}
