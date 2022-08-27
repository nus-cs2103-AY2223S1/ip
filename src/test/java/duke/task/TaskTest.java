package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    private final Task task1 = new Task("buy apples", true);
    private final Task task2 = new Task("buy chicken", false);
    
    @Test
    public void testGetStatusIcon() {
        assertEquals("X", task1.getStatusIcon());
        assertEquals(" ", task2.getStatusIcon());
    }
    
    @Test
    public void testToSaveFormat() {
        assertEquals("| 1 | buy apples", task1.toSaveFormat());
        assertEquals("| 0 | buy chicken", task2.toSaveFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[X] buy apples", task1.toString());
        assertEquals("[ ] buy chicken", task2.toString());
    }
}
