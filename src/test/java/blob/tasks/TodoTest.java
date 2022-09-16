package blob.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import blob.exception.InvalidPriorityException;
import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToString() {
        try {
            Task task = new ToDo("test task", "//h");
            assertEquals(task.toString(), "<HIGH>[T][ ] test task");

            task.markAsDone();
            assertEquals(task.toString(), "<HIGH>[T][\u2713] test task");
        } catch (InvalidPriorityException e) {
            fail();
        }

    }

    @Test
    public void testToFileString() {
        try {
            ToDo task = new ToDo("test task", "//h");
            assertEquals(task.toFileString(), "H | T | 0 | test task");

            task.markAsDone();
            assertEquals(task.toFileString(), "H | T | 1 | test task");
        } catch (InvalidPriorityException e) {
            fail();
        }
    }
}
