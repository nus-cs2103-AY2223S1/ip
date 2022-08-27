package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void instantiateEventObject_invalidDateFormat_exceptionThrown() {
        try {
            assertEquals("[E][ ] Concert (at: Aug 27 2022 06:40:00 PM",
                    new Event("Concert", "Aug 27th 2pm"));
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("Please input the deadline in yyyy-mm-ddTHours:Minutes:Seconds format. " +
                    "E.g 2019-10-15T10:15:00", e.getMessage());
        }
    }
}
