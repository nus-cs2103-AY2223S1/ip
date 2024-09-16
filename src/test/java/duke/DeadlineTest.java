package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DeadlineTest class to test the methods within the Deadline class.
 */
public class DeadlineTest {
    @Test
    public void DeadlineStringTest() throws DukeException {
        String actualString = new Deadline("Testing123", "2022-08-12").toString();
        String expectedString = "[D][ ] Testing123 (by: Aug 12 2022)";

        assertEquals(expectedString, actualString);
    }
}