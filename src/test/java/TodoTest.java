import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void addTodoTest() {
        Todo todo = new Todo("Tutorial 1");
        assertEquals("[T][ ] Tutorial 1", todo.toString());
    }

    @Test
    public void markTodoTest() {
        Todo todo = new Todo("Tutorial 1");
        todo.mark();
        assertEquals("[T][X] Tutorial 1", todo.toString());
    }

    @Test
    public void unmarkTodoTest() {
        Todo todo = new Todo("Tutorial 1");
        todo.mark();
        todo.unmark();
        assertEquals("[T][ ] Tutorial 1", todo.toString());
    }

    @Test
    public void saveTodoTest() {
        Todo todo = new Todo("Tutorial 1");
        assertEquals("T | 0 | Tutorial 1", todo.saveTask());
    }

    @Test
    public void saveTodoTest2() {
        Todo todo = new Todo("Tutorial 1");
        todo.mark();
        assertEquals("T | 1 | Tutorial 1", todo.saveTask());
    }
}
