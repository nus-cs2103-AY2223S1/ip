package doemon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void getSaveString_validSaveString() {
        Task todo = new Todo("This is a test todo");
        String expected = "T | 0 | This is a test todo";
        assertEquals(expected, todo.getSaveString());
    }

    @Test
    public void toString_validString() {
        Task todo = new Todo("This is a test todo");
        String expected = "[T][ ] This is a test todo";
        assertEquals(expected, todo.toString());
    }
}
