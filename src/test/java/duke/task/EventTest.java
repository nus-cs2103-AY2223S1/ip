package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getAsStringArray_makingObjects_normalBehavior() {
        Task task1 = new Event("task one", "1", false);
        Task task2 = new Event("task two", "2", true);
        Task task3 = new Event("task three", "3");

        assertEquals("[E][ ] task one (at: 1)", task1.toString());
        assertEquals("[E][X] task two (at: 2)", task2.toString());
        assertEquals("[E][ ] task three (at: 3)", task3.toString());

        assertArrayEquals(new String[] { "Event", "task one", "false", "1" }, task1.getAsStringArray());
        assertArrayEquals(new String[] { "Event", "task two", "true", "2" }, task2.getAsStringArray());
        assertArrayEquals(new String[] { "Event", "task three", "false", "3" }, task3.getAsStringArray());
    }
}
