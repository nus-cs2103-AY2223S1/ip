import duke.DukeException;
import duke.TaskList;
import duke.task.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addTaskTest(){
        TaskList taskList = new TaskList();
        Task task = new ToDo("homework");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
    }

    @Test
    public void deleteTaskTest(){
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Task newTask = new Event("party", "2022-09-31 1600");
            tasks.add(newTask);
            TaskList taskList = new TaskList(tasks);
            taskList.deleteTask(0);
            assertEquals(0, taskList.size());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getTasksOnDateTest(){
        try {
            TaskList taskList = new TaskList();
            Task task1 = new Event("homework", "2022-09-01 2359");
            taskList.addTask(task1);
            Task task2 = new Event("write", "2022-10-10 1200");
            taskList.addTask(task2);
            assertEquals(1, taskList.getTasksOnDate("2022-09-01").size());
        } catch (DukeException e) {
            fail();
        }
    }
}
