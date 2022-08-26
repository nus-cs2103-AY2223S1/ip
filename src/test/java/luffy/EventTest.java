package luffy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * EventTest class to test the Event class.
 * @author Silas Tay (A0233425M)
 */
public class EventTest {
    /**
     * Tests Event String representation.
     */
    @Test
    public void eventStringTest() {
        Event newTodo = new Event("Test Description", "2022-11-13");
        assertEquals("[E][ ] Test Description (at: 2022-11-13)", newTodo.toString());
    }
}
