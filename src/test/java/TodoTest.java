import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mia.Todo;

public class TodoTest {
    private static final String DUMMY_TODO = "0;;Title;;";

    @Test
    public void saveFormatTest() {
        final Todo todo = Todo.fromSaveFormat(DUMMY_TODO);
        assertEquals("T;;" + DUMMY_TODO, todo.toSaveFormat());
    }

    @Test
    public void stringTest() {
        final Todo todo = Todo.fromSaveFormat(DUMMY_TODO);
        assertEquals("📜 ☐ Title", todo.toString());
    }
}
