package duke.models;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for {@code Todo} class.
 */

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("return book");
        assert todo.toString().equals("[T][ ] return book");
    }
}
