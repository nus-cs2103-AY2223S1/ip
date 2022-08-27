package duke;

import duke.task.Task;
import duke.task.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void get_indexOne_success() {
        Task task = new ToDos("hello", false);
        TaskList tasks = new TaskList(10);
        tasks.add(task);
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void get_indexOutOfBounds_exceptionThrown() {
        try {
            Task task = new ToDos("hello", false);
            TaskList tasks = new TaskList(10);
            tasks.add(task);
            assertEquals(task, tasks.get(5));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Index 5 out of bounds for length 1", e.getMessage());
        }
    }
}
