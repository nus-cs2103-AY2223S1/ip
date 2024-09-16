package drivers;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.NoTaskException;
import exceptions.TumuException;
import tasks.Todo;

public class TaskListTest {
    private TaskList taskList = new TaskList(new ArrayList<>());

    @Test
    public void taskListTest_addTask() {
        taskList.addTask(new Todo("task"));
        assert(taskList.getListSize() == 1);
        assert(taskList.getTasks().get(0) instanceof Todo);
    }

    @Test
    public void taskListTest_deleteTask() {
        try {
            taskList.addTask(new Todo("task"));
            assert(taskList.deleteTask(1) instanceof Todo);
        } catch (TumuException e) {
            fail();
        }
    }

    @Test
    public void taskListTest_deleteTask2() {
        try {
            taskList.deleteTask(0);
            fail();
        } catch (TumuException e) {
            assert(e instanceof NoTaskException);
        }
    }
}
