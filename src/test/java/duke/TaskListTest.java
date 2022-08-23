package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testGetSize() {
        ArrayList<Task> tasks = new ArrayList<>();
        assertEquals(0, new TaskList().getSize());
        tasks.add(new Task("taskTest"));
        assertEquals(1, new TaskList(tasks).getSize());
    }
}
