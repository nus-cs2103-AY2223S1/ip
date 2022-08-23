package duke.test;

import duke.tasklist.TaskList;
import duke.task.Task;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void getTaskTest() {
        ArrayList<Task> temp = new ArrayList<>();
        Task item1 = new Todo("first item");
        temp.add(item1);
        TaskList list = new TaskList(temp);
        assertEquals(item1, list.getTask(0));
    }

    @Test
    public void getSizeTest() {
        ArrayList<Task> temp = new ArrayList<>();
        Task item1 = new Todo("first item");
        Task item2 = new Todo("second item");
        temp.add(item1);
        temp.add(item2);
        TaskList list = new TaskList(temp);
        assertEquals(2, list.getSize());
    }
}
