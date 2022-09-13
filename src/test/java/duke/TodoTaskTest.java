package duke;

import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    @Test
    public void newTodoTest() {
        TodoTask todo = new TodoTask("Test description");
        assertEquals(todo.toString(), "[T][ ] Test description");
    }

    @Test
    public void markTodoTest() {
        TodoTask todo = new TodoTask("Test description");
        todo.markDone();
        assertEquals(todo.toString(), "[T][X] Test description");
    }
}