package duke;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTest() {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("Buy bread");
        tasks.add(todo);
        assertEquals(tasks.size(), 1);
    }

    @Test
    public void deleteTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Buy Bread"));
        TaskList t = new TaskList(tasks);
        t.remove(0);
        assertEquals(tasks.size(), 0);

    }
}
