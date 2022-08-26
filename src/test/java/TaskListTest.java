import Duke.DukeHandler;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.task.Todo;
import org.junit.jupiter.api.Test;
import Duke.DukeException;

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
