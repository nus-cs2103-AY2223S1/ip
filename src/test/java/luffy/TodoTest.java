package luffy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TodoTest class to test the Test class.
 * @author Silas Tay (A0233425M)
 */
public class TodoTest {
    /**
     * Tests Todo String representation.
     */
    @Test
    public void todoStringTest() {
        Todo newTodo = new Todo("Test Description");
        assertEquals("[T][ ] Test Description", newTodo.toString());
    }
}
