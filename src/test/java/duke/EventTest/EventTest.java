package duke.EventTest;

import duke.models.Event;
import duke.models.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Task newEvent = new Event("this is a test ", "2022-08-31");

    @Test
    public void initiateEventTest() {
        assertEquals("[E][ ] this is a test (at: Aug 31 2022)", newEvent.toString());
    }

    @Test
    public void markEventTest() {
        newEvent.setDone();
        assertEquals("[E][X] this is a test (at: Aug 31 2022)", newEvent.toString());
    }
}
