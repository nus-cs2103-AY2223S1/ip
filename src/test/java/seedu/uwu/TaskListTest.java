package seedu.uwu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import uwu.task.Task;
import uwu.task.TaskList;
import uwu.task.ToDos;

public class TaskListTest {
    @Test
    public void taskListToString_zeroTasks_success() {
        assertEquals("you currently have no tasks, feed me <:", new TaskList().taskListToString());
    }

    @Test
    public void taskListToString_nonzeroTasks_success() {
        ArrayList<Task> tasksTemp = new ArrayList<Task>(Arrays.asList(new ToDos("read book"),
                new ToDos("return book")));
        TaskList tasks = new TaskList();
        tasks.setTaskList(tasksTemp);
        assertEquals("\n 1. [T][ ] read book\n 2. [T][ ] return book", tasks.taskListToString());
    }

    @Test
    public void taskListToStorageString_nonzeroTasks_success() {
        ArrayList<Task> tasksTemp = new ArrayList<Task>(Arrays.asList(new ToDos("read book"),
                new ToDos("return book")));
        TaskList tasks = new TaskList();
        tasks.setTaskList(tasksTemp);
        assertEquals("T,0,read book\nT,0,return book", tasks.taskListToStorageString());
    }
}
