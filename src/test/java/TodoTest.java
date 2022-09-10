import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Todo;

public class TodoTest {
    @Test
    public void testEmptyDescription() {
        Todo todo = new Todo("", false);
        assertEquals("", todo.getDescription());
    }

    @Test
    public void testChangeIsDone() {
        Todo todo = new Todo("test", false);
        todo.changeIsDone(true);
        assertEquals(true, todo.getIsDone());
    }

    @Test
    public void testSuccessfulCanChangeIsDone() {
        Todo todo = new Todo("test", false);
        assertEquals(true, todo.canChangeIsDone(true));
    }

    @Test
    public void testFailedCanChangeIsDone() {
        Todo todo = new Todo("test", false);
        assertEquals(false, todo.canChangeIsDone(false));
    }

    @Test
    public void testEmptyToString() {
        Todo todo = new Todo("", false);
        assertEquals("[T][ ] ", todo.toString());
    }

    @Test
    public void testTodoWithDescriptionToString() {
        Todo todo = new Todo("eat", false);
        assertEquals("[T][ ] eat", todo.toString());
    }
}


