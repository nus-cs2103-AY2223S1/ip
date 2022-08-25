package duke.util;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    Task t1 = new ToDo("something");
    Task t2 = new Deadline("iP", "25/08/22");

    @Test
    public void addTask() {
        TaskList list = new TaskList();
        list.add(t1);
        assertEquals(t1, list.lastTaskAdded());
    }

    @Test
    public void deleteTask() {
        TaskList list = new TaskList();
        list.add(t1);
        list.add(t2);
        list.delete(1);
        assertEquals(1, list.getSize());
        assertEquals(t1, list.lastTaskAdded());
    }
}
