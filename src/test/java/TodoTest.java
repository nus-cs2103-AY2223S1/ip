import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Todo;


/**
 * To unit test the Todo class.
 */
public class TodoTest {

    /**
     * Tests the creation of a todo object.
     */
    @Test
    public void testCreateTodo() {
        Todo todo = new Todo("test");
        String result = "[T][O] test";
        assertEquals(result, todo.toString());
    }

    /**
     * Tests the save format of an Event object.
     */
    @Test
    public void testFormatting() {
        Todo todo = new Todo("test");
        String result = "T | O | test\n";
        assertEquals(result, todo.formatFileText());
    }
}
