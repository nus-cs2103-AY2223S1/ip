package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void printTodoTest() {
        assertEquals("T |   | homework", new ToDo("homework", false).toString());
    }

    @Test
    public void markTodoTest() {
        Task todo = new ToDo("read book", false);
        todo.markAsDone();
        assertEquals("T | X | read book", todo.toString());
    }

    @Test
    public void unmarkTodoTest() {
        Task todo = new ToDo("read book", true);
        todo.markAsUndone();
        assertEquals("T |   | read book", todo.toString());
    }





}
