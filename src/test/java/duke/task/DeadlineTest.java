package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.UnexpectedDateTimeFormatException;

class DeadlineTest {
    @Test
    public void constructor_acceptableConstructor_success() throws UnexpectedDateTimeFormatException {
        assertEquals("[D][ ] read book (by: Aug 28 2022 18:00)",
                new Deadline("read book", "28/08/2022 1800").toString());
        assertEquals("[D][ ] read book (by: Aug 28 2022 18:00)",
                new Deadline("read book", "Aug 28 2022 18:00", false).toString());
        assertEquals("[D][X] read book (by: Aug 28 2022 18:00)",
                new Deadline("read book", "Aug 28 2022 18:00", true).toString());
    }

    @Test
    public void constructor_acceptableConstructor_exceptionThrown() {
        try {
            assertEquals("[D][ ] read book (by: Aug 28 2022 18:00)",
                    new Deadline("read book", "28/8/2022 1800").toString());
            fail();
        } catch (UnexpectedDateTimeFormatException e) {
            assertEquals("☹ OOPS!!! Wrong date and time format! Please give in the format DD/MM/YYYY HHmm",
                    e.getMessage());
        }
    }
}
