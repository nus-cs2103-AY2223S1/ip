import chad.task.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask {
    @Test
    public void testToString() {
        Task t;
        t = new Task("Do CS2103");
        assertEquals("[ ] Do CS2103", t.toString());
    }

    @Test
    public void testMarkAsDone() {
        Task t;
        t = new Task("Do CS2103");
        t.markAsDone();
        assertEquals(true, t.getDone());
    }

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
