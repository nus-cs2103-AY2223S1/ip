package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_dummyTodo_success() {
        Todo todo = new Todo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void getType_dummyTodo_success() {
        Todo todo = new Todo("test");
        assertEquals("T", todo.getType());
    }

    @Test
    public void getDate_dummyTodo_success() {
        Todo todo = new Todo("test");
        assertEquals(" ", todo.getDate());
    }

    @Test
    public void isOnDate_dummyTodo_success() {
        Todo todo = new Todo("test");
        assertFalse(todo.isOnDate("2022-09-13"));
    }
}
