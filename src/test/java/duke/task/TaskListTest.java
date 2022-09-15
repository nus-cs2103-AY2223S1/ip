package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void sizeTest() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.add(new Task("Testing1"));
        tasks.add(new Todo("Testing2"));
        assertEquals(2, tasks.size());
    }

    @Test
    public void getTest() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Task t = new Task("Testing1");
        tasks.add(t);
        assertEquals(t, tasks.get(0));
    }
}
