package duke.test;

import duke.command.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void retrieveTaskTest(){
        ArrayList<Task> taskList = new ArrayList<>();
        Task task1 = new Todo("eat breakfast");
        Task task2 = new Event("eat lunch", "12-1pm");
        taskList.add(task1);
        taskList.add(task2);
        TaskList tasks = new TaskList(taskList);
        assertEquals(task1, tasks.retrieveTask(1));
    }

    @Test
    public void getListSizeTest(){
        ArrayList<Task> taskList = new ArrayList<>();
        Task task1 = new Todo("eat breakfast");
        Task task2 = new Event("eat lunch", "12-1pm");
        taskList.add(task1);
        taskList.add(task2);
        TaskList tasks = new TaskList(taskList);
        assertEquals(2, tasks.getListSize());
    }
}
