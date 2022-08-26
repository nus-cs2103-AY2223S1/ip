package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getAsStringArray_makingObjects_normalBehavior() {
        Task task1 = new Deadline("task one", "1", false);
        Task task2 = new Deadline("task two", "2", true);
        Task task3 = new Deadline("task three", "3");

        assertEquals("[D][ ] task one (by: 1)", task1.toString());
        assertEquals("[D][X] task two (by: 2)", task2.toString());
        assertEquals("[D][ ] task three (by: 3)", task3.toString());

        assertArrayEquals(new String[] { "Deadline", "task one", "false", "1" }, task1.getAsStringArray());
        assertArrayEquals(new String[] { "Deadline", "task two", "true", "2" }, task2.getAsStringArray());
        assertArrayEquals(new String[] { "Deadline", "task three", "false", "3" }, task3.getAsStringArray());
    }
}
