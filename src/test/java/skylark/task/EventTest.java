package skylark.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import skylark.skylark.SkylarkException;

public class EventTest {

    @Test
    public void instantiate_dateTime_exceptionThrown() {
        try {
            new Event("Make tea", "Invalid time");
            fail(); // the test should not reach this line
        } catch (SkylarkException e) {
            assertEquals("Cannot parse date", e.getMessage());
        }
    }

    @Test
    public void toString_dateTime_success() throws SkylarkException {
        Event task = new Event("Make tea", "2019-10-15 1800");
        assertEquals("[E] [ ] Make tea (at: Oct 15 2019)", task.toString());
        task.markAsDone();
        assertEquals("[E] [X] Make tea (at: Oct 15 2019)", task.toString());
    }

    @Test
    public void toStringFile_dateTime_success() throws SkylarkException {
        Event task = new Event("Make tea", "2019-10-15 1800");
        assertEquals("E | 0 | Make tea | 2019-10-15 1800", task.toStringFile());
        task.markAsDone();
        assertEquals("E | 1 | Make tea | 2019-10-15 1800", task.toStringFile());
    }

    @Test
    public void toString_dateTimeWTag_success() throws SkylarkException {
        Event task = new Event("Make tea", "2019-10-15 1800", "Test tag!");
        assertEquals("[E] [ ] Make tea TAG: Test tag! (at: Oct 15 2019)", task.toString());
        task.markAsDone();
        assertEquals("[E] [X] Make tea TAG: Test tag! (at: Oct 15 2019)", task.toString());
    }

    @Test
    public void toStringFile_dateTimeWTag_success() throws SkylarkException {
        Event task = new Event("Make tea", "2019-10-15 1800", "Test tag!");
        assertEquals("E | 0 | Make tea | Test tag! | 2019-10-15 1800", task.toStringFile());
        task.markAsDone();
        assertEquals("E | 1 | Make tea | Test tag! | 2019-10-15 1800", task.toStringFile());
    }
}
