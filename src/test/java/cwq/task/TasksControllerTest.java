package cwq.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class TasksControllerTest {
    @Test
    public void getTasksTest() {
        ToDo task1 = new ToDo("CS2103T");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        TasksController controller = new TasksController(tasks);
        assertEquals(controller.getTasks(), tasks);
    }

    @Test
    public void getTaskStringTest() {
        ToDo task1 = new ToDo("CS2103T");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        TasksController controller = new TasksController(tasks);
        assertEquals(controller.getTasksString(tasks), "1. [T][] CS2103T\n");
    }
}
