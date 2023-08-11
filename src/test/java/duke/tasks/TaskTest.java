package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getStatusIcon_unmarkedTodo_success() {
        Todo todo = new Todo("test");
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void changeStatus_markedTodo_success() {
        Todo todo = new Todo("test");
        todo.changeStatus(true);
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void getDescription_dummyTodo_success() {
        Todo todo = new Todo("test");
        assertEquals("test", todo.getDescription());
    }

    @Test
    public void hasKeyword_todoHasKeyword_success() {
        Todo todo = new Todo("hello test");
        assertTrue(todo.hasKeyword("test"));
    }

    @Test
    public void hasKeyword_emptyKeyword_success() {
        Todo todo = new Todo("hello test");
        assertFalse(todo.hasKeyword(""));
    }
}
