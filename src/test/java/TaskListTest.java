import exceptions.EmptyNameException;
import objects.Task;
import org.junit.jupiter.api.Test;
import utils.TaskList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {
    @Test
    public void addTodoWithoutExceptions() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTodo(new String[] {"todo", "wash dishes"});
            List<Task> tasks = taskList.getTasks();
            assertEquals("wash dishes", tasks.get(0).getName());
        } catch (EmptyNameException e) {
            fail("Test Fail: EmptyNameException is thrown.");
        }
    }

    @Test
    public void addTodoWithEmptyNameException() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTodo(new String[] {"todo"});
            fail("Test Fail: EmptyNameException is not thrown.");
        } catch (EmptyNameException e) {
            assertNotNull(e);
        }
    }
}
