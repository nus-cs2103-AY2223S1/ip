package luffy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventStringTest() {
        Event newTodo = new Event("Test Description", "2022-11-13");
        assertEquals("[E][ ] Test Description (at: 2022-11-13)", newTodo.toString());
    }
}
