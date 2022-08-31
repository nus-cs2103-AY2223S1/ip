package blob.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToString() {
        Task task = new ToDo("test task");
        assertEquals(task.toString(), "[T][ ] test task");

        task.markAsDone();
        assertEquals(task.toString(), "[T][✓] test task");

    }

    @Test
    public void testToFileString() {
        ToDo task = new ToDo("test task");
        assertEquals(task.toFileString(), "T | 0 | test task");

        task.markAsDone();
        assertEquals(task.toFileString(), "T | 1 | test task");
    }
}
