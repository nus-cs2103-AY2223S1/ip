
package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.task.*;
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
    public void toStringTest() {
        ToDo task1 = new ToDo("CS2103T");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        TasksController controller = new TasksController(tasks);
        assertEquals(controller.toString(), "1. [T][] CS2103T\n");
    }
}
