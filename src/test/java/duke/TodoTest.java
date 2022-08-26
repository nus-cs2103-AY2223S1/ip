package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void newTodoTest() {
        String name = "test1";
        Todo newTodo = new Todo(name);
        assertEquals("[T][ ] " + name, newTodo.toString());
    }

    @Test
    public void newTodoTest_markTest() {
        String name = "test1";
        Todo newTodo = new Todo(name);
        newTodo.markComplete();
        assertEquals("[T][X] " + name, newTodo.toString());
    }
}
