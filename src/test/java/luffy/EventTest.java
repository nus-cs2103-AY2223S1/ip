package luffy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

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
        Event newTodo = new Event("Test Description", LocalDate.of(2022, 11, 13));
        assertEquals("[E][ ] Test Description (at: 2022-11-13)", newTodo.toString());
    }
}
