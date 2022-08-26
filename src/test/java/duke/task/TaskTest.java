package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void markAsDone_togglingDoneAndNotDone_normalBehavior() {
        Task task1 = new Task("task one", false);
        Task task2 = new Task("task two", true);
        Task task3 = new Task("task three");

        assertEquals("[ ] task one", task1.toString());
        assertEquals("[X] task two", task2.toString());
        assertEquals("[ ] task three", task3.toString());

        task1.markAsDone();
        task2.markAsNotDone();
        task3.markAsNotDone();

        assertEquals("[X] task one", task1.toString());
        assertEquals("[ ] task two", task2.toString());
        assertEquals("[ ] task three", task3.toString());

        task1.markAsDone();
        task2.markAsNotDone();
        task3.markAsNotDone();

        assertEquals("[X] task one", task1.toString());
        assertEquals("[ ] task two", task2.toString());
        assertEquals("[ ] task three", task3.toString());

        task1.markAsNotDone();
        task2.markAsDone();
        task3.markAsDone();

        assertEquals("[ ] task one", task1.toString());
        assertEquals("[X] task two", task2.toString());
        assertEquals("[X] task three", task3.toString());
    }

    @Test
    public void getAsStringArray_makingObjects_normalBehavior() {
        Task task1 = new Task("task one", false);
        Task task2 = new Task("task two", true);
        Task task3 = new Task("task three");

        assertArrayEquals(new String[] { "Task", "task one", "false" }, task1.getAsStringArray());
        assertArrayEquals(new String[] { "Task", "task two", "true" }, task2.getAsStringArray());
        assertArrayEquals(new String[] { "Task", "task three", "false" }, task3.getAsStringArray());
    }
}
