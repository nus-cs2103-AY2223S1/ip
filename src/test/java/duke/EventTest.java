package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void descriptionTest() throws DukeException {
        String expectedString = "[E][] project meeting (at: Mon 2-4pm)";
        Event expectedEvent = new Event("project meeting", "Mon 2-4pm");

        assertEquals(expectedEvent.toString(), expectedString);
    }

    @Test
    public void anotherDescriptionTest() throws DukeException {
        String expectedString = "[E][] study session (at: Tues 3-6pm)";
        Event expectedEvent = new Event("study session", "Tues 3-6pm");

        assertEquals(expectedEvent.toString(), expectedString);
    }



}
