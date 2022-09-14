package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_newTodo_returnsCorrectString() {
        Todo todo = new Todo("Party");
        assertEquals("[T][ ] Party", todo.toString());
    }

    @Test
    public void toStorageString_newTodo_returnsCorrectStorageString() {
        Todo event = new Todo("Party");
        assertEquals("T | 0 | Party", event.toStorageString());
    }
}
