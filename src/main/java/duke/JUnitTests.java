package duke;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.ParseException;
import static org.testng.AssertJUnit.assertEquals;
import task.ToDos;


public class JUnitTests {

    @Test
    public void addAddsTasks() {
        TaskList taskList = new TaskList();
        ToDos todo = new ToDos("homework");
        assertEquals(taskList.size(), 0);
        taskList.add(todo);
        assertEquals(taskList.size(), 1);
        taskList.add(todo);
        taskList.add(todo);
        taskList.add(todo);
        assertEquals(taskList.size(), 4);

    }
    @Test
    public void removeRemovesTasks() {
        TaskList taskList = new TaskList();
        ToDos todo = new ToDos("homework");
        assertEquals(taskList.size(), 0);
        taskList.add(todo);
        assertEquals(taskList.size(), 1);
        taskList.add(todo);
        taskList.add(todo);
        taskList.add(todo);
        taskList.remove(3);
        taskList.remove(2);
        assertEquals(taskList.size(), 2);

    }
}
