package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void taskWordTest() {
        assertEquals("todo", Todo.TASK_WORD);
    }

    @Test
    public void taskTypeTest() {
        Todo todo = new Todo("test", true);
        assertEquals(todo.getTaskWord(), "todo");
    }

    @Test
    public void getTimeTest() {
        Todo todo = new Todo("test1", true);
        assertEquals(Optional.empty(), todo.getTime());
    }

    @Test
    public void toStringTest() {
        Todo todo = new Todo("test1", true);
        assertEquals("[T][X] test1", todo.toString());
    }
}
