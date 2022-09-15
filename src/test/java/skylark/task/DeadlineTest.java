package skylark.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import skylark.skylark.SkylarkException;

public class DeadlineTest {

    @Test
    public void instantiate_dateTime_exceptionThrown() {
        try {
            new Deadline("Make tea", "Invalid time");
            fail(); // the test should not reach this line
        } catch (SkylarkException e) {
            assertEquals("Cannot parse date", e.getMessage());
        }
    }

    @Test
    public void toString_dateTime_success() throws SkylarkException {
        Deadline task = new Deadline("Make tea", "2019-10-15 1800");
        assertEquals("[D] [ ] Make tea (by: Oct 15 2019)", task.toString());
        task.markAsDone();
        assertEquals("[D] [X] Make tea (by: Oct 15 2019)", task.toString());
    }

    @Test
    public void toStringFile_dateTime_success() throws SkylarkException {
        Deadline task = new Deadline("Make tea", "2019-10-15 1800");
        assertEquals("D | 0 | Make tea | 2019-10-15 1800", task.toStringFile());
        task.markAsDone();
        assertEquals("D | 1 | Make tea | 2019-10-15 1800", task.toStringFile());
    }

    @Test
    public void toString_dateTimeWTag_success() throws SkylarkException {
        Deadline task = new Deadline("Make tea", "2019-10-15 1800", "Test tag!");
        assertEquals("[D] [ ] Make tea TAG: Test tag! (by: Oct 15 2019)", task.toString());
        task.markAsDone();
        assertEquals("[D] [X] Make tea TAG: Test tag! (by: Oct 15 2019)", task.toString());
    }

    @Test
    public void toStringFile_dateTimeWTag_success() throws SkylarkException {
        Deadline task = new Deadline("Make tea", "2019-10-15 1800", "Test tag!");
        assertEquals("D | 0 | Make tea | Test tag! | 2019-10-15 1800", task.toStringFile());
        task.markAsDone();
        assertEquals("D | 1 | Make tea | Test tag! | 2019-10-15 1800", task.toStringFile());
    }
}
