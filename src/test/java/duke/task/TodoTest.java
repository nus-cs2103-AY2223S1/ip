package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        String description = "eat lunch";
        assertEquals("[T][ ] " + description, new Todo(description).toString());
    }

    @Test
    public void testGetTask() {
        String done = "1";
        String description = "sleep";

        String expected = String.format("T | %s | %s", done, description);
        String actual = new Todo(true, description).getTask();
        
        assertEquals(expected, actual);
    }
}
