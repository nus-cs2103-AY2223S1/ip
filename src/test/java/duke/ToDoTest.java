package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] Do a todo task", new ToDo("Do a todo task", false).toString());
    }
}
