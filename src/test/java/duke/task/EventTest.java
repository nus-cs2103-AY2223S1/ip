package duke.task;

import duke.exception.UnexpectedDateTimeFormatException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EventTest {
    @Test
    public void constructor_acceptableConstructor_success() throws UnexpectedDateTimeFormatException {
        assertEquals("[E][ ] project meeting (at: Aug 28 2022 18:00)",
                new Event("project meeting", "28/08/2022 1800").toString());
        assertEquals("[E][ ] project meeting (at: Aug 28 2022 18:00)",
                new Event("project meeting", "Aug 28 2022 18:00", false).toString());
        assertEquals("[E][X] project meeting (at: Aug 28 2022 18:00)",
                new Event("project meeting", "Aug 28 2022 18:00", true).toString());
    }

    @Test
    public void constructor_acceptableConstructor_exceptionThrown() {
        try {
            assertEquals("[E][ ] project meeting (at: Aug 28 2022 18:00)",
                    new Event("project meeting", "28/8/2022 1800").toString());
            fail();
        } catch (UnexpectedDateTimeFormatException e) {
            assertEquals("☹ OOPS!!! Wrong date and time format! Please give in the format DD/MM/YYYY HHmm",
                    e.getMessage());
        }
    }
}