package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void toString_nonYYYYMMDD_exceptionThrow() {
        try {
            assertEquals("[E][ ] testing (at: blah)",
                    new Event("testing", "blah").toString());
            fail();
        } catch (Exception e) {
            assertEquals("date after /at should be in YYYY-MM-DD Format.", e.getMessage());
        }
    }

    @Test
    public void toString_success() throws DukeException {
        assertEquals("[E][ ] testing (at: Aug 20 2022)",
                new Event("testing", "2022-08-20").toString());
    }

    @Test
    public void testStorageStringConversion() throws DukeException {
        assertEquals("E | 0 | testing | 2022-08-20",
                new Event("testing", "2022-08-20").toStorageString());
    }
}
