package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void newToDo() {
        Task task = new ToDo("sleep");
        assertEquals("[T][ ] sleep", task.toString());
    }

    @Test
    public void markToDo() {
        TaskList tasks = new TaskList();
        Task task = new ToDo("take a walk");
        tasks.addTask(task);
        tasks.markTask(1, true);
        assertEquals("[T][X] take a walk", task.toString());
    }
}

