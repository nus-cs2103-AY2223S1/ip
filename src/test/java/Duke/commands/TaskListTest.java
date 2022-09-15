package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.tasks.Task;
import Duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {


    @Test
    public void add() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task tempTask = new Todo("");
        TaskList taskList = new TaskList(tasks);
        Task addedTask = taskList.addTask(tempTask);
        assertNotNull(taskList);
    }

    @Test
    public void isValidTaskNumbertest() {
        TaskList taskList = new TaskList(Arrays.asList(null, null));
        assertFalse(taskList.isValidTaskNumber(10));
    }


}

