package luffy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoStringTest() {
        Todo newTodo = new Todo("Test Description");
        assertEquals("[T][ ] Test Description", newTodo.toString());
    }
}
