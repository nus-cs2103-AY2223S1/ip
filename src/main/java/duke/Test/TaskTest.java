package duke.Test;
import duke.Task.Task;
import duke.Task.Todo;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private Task task = new Todo("play game");
    @Test
    public void newTaskTest() {
        assertEquals(" ", task.getStatusIcon());
    }
    @Test
    public void testMarkTask() {
        task.toggleStatus();
        assertEquals("X", task.getStatusIcon());
    }

}
