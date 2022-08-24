package duke.modules.todos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void toString_notDone() {
        Todo todo = new Todo("Abacaba", false);
        assertEquals("[T][ ] Abacaba", todo.toString());
    }

    @Test
    public void toString_done() {
        Todo todo = new Todo("Peanuts", true);
        assertEquals("[T][X] Peanuts", todo.toString());
    }

    @Test
    public void toString_default() {
        Todo todo = new Todo("Cherries");
        assertEquals("[T][ ] Cherries", todo.toString());
    }

    @Test
    public void flatPack_smokeTest() {
        Todo todo = new Todo("Mangoes");
        assertIterableEquals(List.of("T", "-", "Mangoes"), todo.flatPack());
    }

    @Test
    public void hydrate_smokeTest() {
        Todo todo = new Todo(List.of("T", "-", "Bananas"));
        assertEquals("[T][ ] Bananas", todo.toString());
    }

    @Test
    public void hydrate_wrongType_illegalArgumentException() {
        try {
            Todo todo = new Todo(List.of("X", "-", "Oranges")); /* should throw */
            System.out.println(todo);
            fail("Did not throw exception");
        } catch (Exception e) {
            assertTrue(e.getMessage().startsWith("Trying to hydrate non-todo as todo: "));
        }
    }
}