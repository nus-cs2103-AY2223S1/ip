package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TaskListTest {
    @Test
    public void addTask() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("test Todo"));
        String actual = tasks.toString();
        String expected = "1.[T][ ] test Todo";
        assertEquals(expected, actual);
    }
}
