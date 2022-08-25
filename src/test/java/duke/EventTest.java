package duke;

import duke.task.Event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEvent() {
        Event testEvent =  new Event("project meeting", "Mon 2-4pm");
        assertEquals(testEvent.taskInfo(), "[E] [ ] project meeting (at:Mon 2-4pm)");
    }


}
