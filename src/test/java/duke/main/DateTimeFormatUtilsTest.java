package duke.main;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeFormatUtilsTest {
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
