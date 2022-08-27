package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void EventObjectTest() {
        Event eventObject = new Event("Event", "at Monday 3pm");
        String actualOutput = "[E][ ] Event (at: Monday 3pm)";
        assertEquals(eventObject.toString(), actualOutput);
    }
}