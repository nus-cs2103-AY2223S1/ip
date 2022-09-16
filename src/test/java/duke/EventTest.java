package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {

    @Test
    public void testEvent() {
        Event testEvent =  new Event("project meeting", "Mon 2-4pm");
        assertEquals(testEvent.taskInfo(), "[E] [ ] [LOW] project meeting (at:Mon 2-4pm)");
    }

    @Test
    public void testMarkUnmarkEvent() {
        Event testEvent =  new Event("project meeting", "Mon 2-4pm");
        testEvent.markAsDone();
        assertEquals(testEvent.taskInfo(), "[E] [X] [LOW] project meeting (at:Mon 2-4pm)");
        testEvent.markAsNotDone();
        assertEquals(testEvent.taskInfo(), "[E] [ ] [LOW] project meeting (at:Mon 2-4pm)");
    }

    @Test
    public void testChangeEventPriority() {
        Event testEvent =  new Event("project meeting", "Mon 2-4pm");
        testEvent.setTaskPriority("high");
        assertEquals(testEvent.getTaskPriority(), "HIGH");
    }


}
