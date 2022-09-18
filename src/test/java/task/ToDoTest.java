package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] homework", new ToDo("homework", false).toString());
    }

    @Test
    public void testGetDescription() {
        assertEquals("T | F | homework\n", new ToDo("homework", false).getDescription());
    }
}
