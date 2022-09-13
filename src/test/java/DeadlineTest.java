import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.DukeException;

/**
 * To unit test the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the creation of a Deadline object.
     *
     * @throws DukeException not thrown.
     */
    @Test
    public void testCreateDeadline() throws DukeException {
        Deadline deadline = new Deadline("test", "2000-12-12 12:12");
        String result = "[D][O] test (by: DECEMBER 12 2000 12:12)";
        assertEquals(result, deadline.toString());
    }

    /**
     * Tests the save format of an Deadline object.
     *
     * @throws DukeException not thrown.
     */
    @Test
    public void testFormatting() throws DukeException {
        Deadline deadline = new Deadline("test", "2000-12-12 12:12");
        String result = "D | O | test | 2000-12-12 12:12\n";
        assertEquals(result, deadline.formatFileText());
    }

    /**
     * Tests that an exception is thrown if the "/by" param is
     * in an invalid format.
     */
    @Test
    public void testIncorrectDateFormat() {
        Throwable exception = assertThrows(Throwable.class, () -> {
            new Deadline("test", "tmr");
        });
        assertEquals("Invalid Date Format (YYYY-MM-DD HH:MM required)." , exception.getMessage());
    }

}
