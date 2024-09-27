package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStorageString_notDone() throws DukeException {
        Todo todo = new Todo("todo", false);
        assertEquals("T | 0 | todo", todo.getStorageString());
    }

    @Test
    public void testStorageString_done() throws DukeException {
        Todo todo = new Todo("todo", true);
        assertEquals("T | 1 | todo", todo.getStorageString());
    }
}
