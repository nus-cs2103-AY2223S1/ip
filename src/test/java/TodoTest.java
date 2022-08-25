import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void addTest() {
        assertEquals(new Todo("read book").toString(),
                "[T][ ] read book");
    }

    @Test
    public void markTest() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals(todo.toString(),
                "[T][X] read book");
    }

    @Test
    public void unmarkTest() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        todo.unmarkTask();
        assertEquals(todo.toString(),
                "[T][ ] read book");
    }
}
