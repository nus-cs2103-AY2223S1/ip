import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void add_todo_success() {
        Todo todo = new Todo("Tutorial 1");
        assertEquals("[T][ ] Tutorial 1", todo.toString());
    }

    @Test
    public void mark_todo_success() {
        Todo todo = new Todo("Tutorial 1");
        todo.mark();
        assertEquals("[T][X] Tutorial 1", todo.toString());
    }

    @Test
    public void unmark_todo_success() {
        Todo todo = new Todo("Tutorial 1");
        todo.mark();
        todo.unmark();
        assertEquals("[T][ ] Tutorial 1", todo.toString());
    }

    @Test
    public void save_markedTodo_success() {
        Todo todo = new Todo("Tutorial 1");
        assertEquals("T | 0 | 0 | Tutorial 1", todo.saveTask());
    }

    @Test
    public void save_unmarkedTodo_success() {
        Todo todo = new Todo("Tutorial 1");
        todo.mark();
        assertEquals("T | 1 | 0 | Tutorial 1", todo.saveTask());
    }

    @Test
    public void setHighPriority_todo_success() {
        Todo todo = new Todo("Tutorial 1");
        todo.setHighPriority();
        assertEquals("[T][ ][!] Tutorial 1", todo.toString());
    }
}
