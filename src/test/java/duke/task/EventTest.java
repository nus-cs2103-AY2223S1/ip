package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void getTaskTest() {
        Event event = new Event("hello", "tomorrow");


        assertEquals("[E] [ ] hello (at: tomorrow)", event.getTask());
    }
}
