package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[ ] watch tv", new Task("watch tv").toString());
    }

    @Test
    public void testStatus() {
        Task task = new Task("watch tv");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
        task.markAsNotDone();
        assertEquals(" ", task.getStatusIcon());
    }
}
