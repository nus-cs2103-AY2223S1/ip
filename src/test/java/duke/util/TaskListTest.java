package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;

public class TaskListTest {
    private Task t1 = new ToDo("something");
    private Task t2 = new Deadline("iP", "25/08/22");

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
