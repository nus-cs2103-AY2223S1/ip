package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testGetSize() {
        ArrayList<Task> arr = new ArrayList<>();
        assertEquals(0, new TaskList().getSize());
        arr.add(new Task("taskTest"));
        assertEquals(1, new TaskList(arr).getSize());
    }
}
