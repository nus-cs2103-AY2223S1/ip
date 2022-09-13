package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_nonyyyymmdd_exceptionThrow() {
        try {
            assertEquals("[D][ ] testing (by: blah)",
                    new Deadline("testing", "blah").toString());
            fail();
        } catch (Exception e) {
            assertEquals("Date after /by should be in YYYY-MM-DD Format nya.", e.getMessage());
        }
    }

    @Test
    public void toString_success() throws DukeException {
        assertEquals("[D][ ] testing (by: Aug 20 2022)",
                new Deadline("testing", "2022-08-20").toString());
    }

    @Test
    public void testStorageStringConversion() throws DukeException {
        assertEquals("D | 0 | testing | 2022-08-20", new Deadline("testing", "2022-08-20").toStorageString());
    }
}
