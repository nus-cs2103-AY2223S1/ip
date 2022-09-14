package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void addTest() {
        TaskList tasks = new TaskList(new ArrayList<Task>(), new Storage("data/dummy.txt"));
        tasks.add(new Task("task"));
        assertEquals(tasks.size(), 1);
    }

    @Test
    public void removeTest() {
        TaskList tasks = new TaskList(new ArrayList<Task>(), new Storage("data/dummy.txt"));
        tasks.add(new Task("task"));
        tasks.add(new Deadline("deadline", "Aug 06 2020"));
        tasks.remove(0);
        assertEquals(tasks.size(), 1);
    }

}
