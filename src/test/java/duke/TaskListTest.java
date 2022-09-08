package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskListTest {

    @Test
    public void testGetSize() {
        ArrayList<Task> tasks = new ArrayList<>();
        assertEquals(0, new TaskList().getSize());
        tasks.add(new Task("taskTest"));
        assertEquals(1, new TaskList(tasks).getSize());
    }
}
