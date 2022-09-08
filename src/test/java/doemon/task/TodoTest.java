package doemon.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void saveStringTest() {
        Task todo = new Todo("This is a test todo");
        String expected = "T | 0 | This is a test todo";
        assertEquals(expected, todo.saveString());
    }

    @Test
    public void toStringTest() {
        Task todo = new Todo("This is a test todo");
        String expected = "[T][ ] This is a test todo";
        assertEquals(expected, todo.toString());
    }
}
