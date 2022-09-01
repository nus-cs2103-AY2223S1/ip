package Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void addToDoTest() {
        Todo todo = new Todo("task 1");
        assertEquals("[T][ ] task 1", todo.toString());
    }
}
