import duke.Deadline;
import duke.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void newTodoTest() {
        Todo todo = new Todo("go school");
        assertEquals(todo.toString(), "[T][ ] go school");
    }

    @Test
    public void markDeadlineTest() {
        Todo todo = new Todo("go school");
        todo.markAsDone();
        assertEquals(todo.toString(), "[T][X] go school");
    }
}
