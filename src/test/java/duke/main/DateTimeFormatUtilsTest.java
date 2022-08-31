package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

/**
 * Test class for DateTimeFormatUtils class.
 */
public class DateTimeFormatUtilsTest {

    /**
     * Tests parseDate method with invalid input.
     * Exception should be thrown with error message equal to the expected output.
     */
    @Test
    public void parseDate_invalidDateFormat_exceptionThrown() {
        try {
            DateTimeFormatUtils.parseDate("This is an invalid Input");
            fail();
        } catch (DukeException de) {
            assertEquals("\tDate Format Police here!!"
                            + "\n\tYour deadline has to be in YYYY-MM-DD HHmm format!!",
                    de.getMessage());
        }
    }
}
