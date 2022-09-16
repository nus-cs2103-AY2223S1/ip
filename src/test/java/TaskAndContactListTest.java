import gina.GinaException;
import gina.TaskAndContactList;
import gina.task.Event;
import gina.task.Task;
import gina.task.ToDo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskAndContactListTest {
    @Test
    public void addTaskTest(){
        TaskAndContactList taskAndContactList = new TaskAndContactList();
        Task task = new ToDo("homework");
        taskAndContactList.addTask(task);
        assertEquals(1, taskAndContactList.tasksSize());
    }

    @Test
    public void deleteTaskTest(){
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Task newTask = new Event("party", "2022-09-31 1600");
            tasks.add(newTask);
            TaskAndContactList taskAndContactList = new TaskAndContactList(tasks, new ArrayList<>());
            taskAndContactList.deleteTask(0);
            assertEquals(0, taskAndContactList.tasksSize());
        } catch (GinaException e) {
            fail();
        }
    }

    @Test
    public void getTasksOnDateTest(){
        try {
            TaskAndContactList taskAndContactList = new TaskAndContactList();
            Task task1 = new Event("homework", "2022-09-01 2359");
            taskAndContactList.addTask(task1);
            Task task2 = new Event("write", "2022-10-10 1200");
            taskAndContactList.addTask(task2);
            assertEquals(1, taskAndContactList.getTasksOnDate("2022-09-01").tasksSize());
        } catch (GinaException e) {
            fail();
        }
    }
}
