package duke;

import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    @Test
    public void newTodoTest() {
        TodoTask todo = new TodoTask("Test description");
        assertEquals("[T][ ] Test description", todo.toString() );
    }

    @Test
    public void markTodoTest() {
        TodoTask todo = new TodoTask("Test description");
        todo.markDone();
        assertEquals("[T][X] Test description", todo.toString());
    }
}