package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        String[] args = {"testing", "today"};
        Event task = new Event(args);
        assertEquals("[E][ ] testing at: today", task.toString());
    }
}
