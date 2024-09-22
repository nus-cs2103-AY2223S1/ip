package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_undone() {
        ToDo todo = new ToDo("description");
        todo.markAsUndone();
        assertEquals("[T][ ] description", todo.toString());
    }

    @Test
    public void toString_done() {
        ToDo todo = new ToDo("description");
        todo.markAsDone();
        assertEquals("[T][X] description", todo.toString());
    }
}
