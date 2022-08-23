import duke.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testCreateTodo() {
        Todo todo = new Todo("test");
        String result = "[T][O] test";
        assertEquals(result, todo.toString());
    }

    @Test
    public void testFormatting() {
        Todo todo = new Todo("test");
        String result = "T | O | test\n";
        assertEquals(result, todo.formatFileText());
    }
}
