package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo todo = new Todo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void getType() {
        Todo todo = new Todo("test");
        assertEquals("T", todo.getType());
    }

    @Test
    public void getDate() {
        Todo todo = new Todo("test");
        assertEquals(" ", todo.getDate());
    }
}
