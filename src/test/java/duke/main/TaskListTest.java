package duke.main;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void testAddTaskAndGetTaskList() {
        ArrayList<Task> testList = new ArrayList<>();
        Task task = new ToDo("test");
        testList.add(task);
        TaskList list = new TaskList();
        list.addTask(task);
        assertEquals(testList, list.getTaskList());
    }
}
