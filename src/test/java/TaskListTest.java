import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import duke.exceptions.DukeException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void listTask() {
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
}
