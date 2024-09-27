package chad.task;

import chad.task.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test task class
 */
public class TestTask {
    /**
     * Check if task instance can be created by asserting its toString()
     */
    @Test
    public void testToString() {
        Task t;
        t = new Task("Do CS2103");
        assertEquals("[ ] Do CS2103", t.toString());
    }

    /**
     * Check if task markAsDone() works
     */
    @Test
    public void testMarkAsDone() {
        Task t;
        t = new Task("Do CS2103");
        t.markAsDone();
        assertEquals(true, t.getDone());
    }

    /**
     * Check if task markAsUndone() works by testing both when task isDone attribute is false and true
     */
    @Test
    public void testMarkAsUndone() {
        Task t;
        t = new Task("Walk the dog");
        t.markAsUndone();
        assertEquals(false, t.getDone());

        t = new Task("Do CS2103");
        t.markAsDone();
        t.markAsUndone();
        assertEquals(false, t.getDone());
    }
}
