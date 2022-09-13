package Duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] eat shit", new ToDo("eat shit", null).toString());
    }
}
