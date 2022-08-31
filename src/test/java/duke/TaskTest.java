package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * TaskTest class to test Task object methods.
 */
public class TaskTest {

    /**
     * Tests for marking a Task object as done.
     */
    @Test
    public void markAsDoneTaskCrossShown() {
        Task task1 = new Todo("Sample Task");
        task1.markAsDone();
        assertEquals("[T][X] Sample Task", task1.toString());
    }

    /**
     * Tests for marking a Task object as not done.
     */
    @Test
    public void markAsNotDoneTaskCrossRemoved() {
        Task task1 = new Todo("Sample Task");
        task1.markAsDone();
        task1.markAsNotDone();
        assertEquals("[T][ ] Sample Task", task1.toString());
    }
}
