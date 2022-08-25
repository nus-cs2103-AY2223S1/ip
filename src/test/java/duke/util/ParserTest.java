package duke.util;

import duke.exception.DukeDateTimeFormatException;
import duke.exception.DukeTaskDateTimeMissingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    Parser testSubject = new Parser();

    @Test
    public void getByDate_missingTimeInput_exceptionThrown() {
        String input = "todo read book /by    ";
        try {
            Parser.getByDate(input);
        } catch (DukeTaskDateTimeMissingException exception) {
            assertEquals(exception.getMessage(), Parser.TASK_DATE_TIME_MISSING_ERROR_MESSAGE);
        } catch (Exception exception) {
            fail();
        }
    }
}
