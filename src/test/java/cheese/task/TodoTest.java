package cheese.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toFileStringConversion() {
        Todo todo = new Todo(true, "Drink water");
        assertEquals("todo // T // Drink water", todo.toFileString());
    }

    @Test
    public void toStringConversion() {
        Todo todo = new Todo(true, "Drink water");
        assertEquals("[T][X] Drink water", todo.toString());
    }
}
