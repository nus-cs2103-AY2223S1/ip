package duke.model;
import duke.models.Deadline;
import duke.models.DeadlineParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineParseTest {
    @Test
    public void parseDeadline_invalidDate_exceptionThrown() {
        try {
            DeadlineParser deadlineParser = new DeadlineParser();
            assertEquals(new Deadline("taskA", LocalDate.parse("2020-06-06")),
                    deadlineParser.parseDeadline("add deadline taskA /by 2020-06-06"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Your date must be a valid date in dd/MM/yyyy format",
                    e.getMessage());
        }
    }
}
