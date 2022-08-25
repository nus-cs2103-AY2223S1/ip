package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void getTaskTest() {
        Event event = new Event("hello", "tomorrow");


        assertEquals("[E] [ ] hello (at: tomorrow)", event.getTask());
    }
}