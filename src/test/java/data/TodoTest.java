package data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testMarkDone() {
        ToDo todo = new ToDo("title", false);
        todo.markDone();
        assertTrue(todo.isDone());
    }
}
