package duke.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getAsStringArray_makingObjects_normalBehavior() {
        Task task1 = new Deadline("task one", "not-date-1", false);
        Task task2 = new Deadline("task two", "not-date-2", true);
        Task task3 = new Deadline("task three", "not-date-3");

        assertEquals("[D][ ] task one (by: not-date-1)", task1.toString());
        assertEquals("[D][X] task two (by: not-date-2)", task2.toString());
        assertEquals("[D][ ] task three (by: not-date-3)", task3.toString());

        assertArrayEquals(new String[] { "Deadline", "task one", "false", "not-date-1" }, task1.getAsStringArray());
        assertArrayEquals(new String[] { "Deadline", "task two", "true", "not-date-2" }, task2.getAsStringArray());
        assertArrayEquals(new String[] { "Deadline", "task three", "false", "not-date-3" }, task3.getAsStringArray());
    }
}
