import duke.exceptions.DukeDateException;
import duke.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.exceptions.DukeException;

import java.util.ArrayList;
import java.util.Arrays;


public class TaskListTest {
    @Test
    public void testListTask() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>(
                    Arrays.asList(new Todo("Description", false),
                            new Todo("gym", false)));
            TaskList list = new TaskList(tasks);
            assertEquals(tasks, list.listTasks());
        } catch (DukeException e) {
            assertEquals(e.getMessage(), e.getMessage());
        }
    }

    @Test
    public void testDeleteTask() {
        try {
            Task deadline = new Deadline("BCA", "2022-08-26 15:00", false);
            ArrayList<Task> tasks = new ArrayList<Task>(
                    Arrays.asList(new Todo("ABC", false), deadline
                            ));
            TaskList list = new TaskList(tasks);
            assertEquals(list.delete(2), deadline);
        } catch (DukeException e) {
        } catch (TaskNotFoundException e) {
            assertEquals("This task cannot be found in our list! Check your index!", e.getMessage());
        } catch (DukeDateException e) {
        }
    }
}
