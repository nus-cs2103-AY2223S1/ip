package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    @Test
    public void testMarkDone() {
        ToDo todo = new ToDo("title", false);
        todo.markDone();
        assertTrue(todo.isDone());
    }
}
