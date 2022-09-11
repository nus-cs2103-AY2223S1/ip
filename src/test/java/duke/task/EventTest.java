package duke.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getAsStringArray_makingObjects_normalBehavior() {
        Task task1 = new Event("task one", "not-date-1", false);
        Task task2 = new Event("task two", "not-date-2", true);
        Task task3 = new Event("task three", "not-date-3");

        assertEquals("[E][ ] task one (at: not-date-1)", task1.toString());
        assertEquals("[E][X] task two (at: not-date-2)", task2.toString());
        assertEquals("[E][ ] task three (at: not-date-3)", task3.toString());

        assertArrayEquals(new String[] { "Event", "task one", "false", "not-date-1" }, task1.getAsStringArray());
        assertArrayEquals(new String[] { "Event", "task two", "true", "not-date-2" }, task2.getAsStringArray());
        assertArrayEquals(new String[] { "Event", "task three", "false", "not-date-3" }, task3.getAsStringArray());
    }
}
