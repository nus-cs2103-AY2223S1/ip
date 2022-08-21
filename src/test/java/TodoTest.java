import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void printTodo_normalInput_success() {
        assertEquals("[T][ ] a todo task", new Todo("a todo task").toString());
    }

    @Test
    public void saveTodo_normalInput_success() {
        assertEquals("T|0|a todo task", new Todo("a todo task").toFileFormatString());
    }
}