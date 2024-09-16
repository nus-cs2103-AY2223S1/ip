package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void stringToEvent() {
        String testString = "[E][ ] project meeting (at: Aug 6th 2-4pm)";
        Event convertedString = new Event("","");
        try {
            convertedString = Event.stringToEvent(testString);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(testString, convertedString.toString());
    }
}