package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testGetStatus() {
        Todo todo = new Todo("null");
        todo.markDone();
        assertEquals("[T][X]", todo.getStatus());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("null");
        todo.markDone();
        assertEquals("[T][X] null", todo.toString());
    }
}
