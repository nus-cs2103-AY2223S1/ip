package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseDateTime_sampleDateTime() throws DukeException {
        assertEquals(Parser.parseDateTime("2/12/2019 1800").toString(), "2019-12-02T18:00");
    }

    @Test
    public void parseDateTime_notDateTime_exceptionThrown() {
        try {
            Parser.parseDateTime("soon");
            fail("No exception thrown.");
        } catch (DukeException e) {
            assertEquals(e.toString(), "Oops! Please specify the date and time in YYYY-MM-DD TTTT format.");
        }
    }

    @Test
    public void parseCommand_unknownCommand_exceptionThrown() {
        try {
            Parser.parseCommand("notACommand test", new TaskList());
            fail("No exception thrown.");
        } catch (DukeException e) {
            assertEquals(e.toString(), "Oops! I'm sorry, but I don't know what that means!");
        }
    }
}
