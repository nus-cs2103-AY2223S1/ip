package roger.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import roger.commands.AddEventCommand;
import roger.exceptions.RogerInvalidInputException;


public class ParserTest {
    @Timeout(1)
    @Test
    public void parse_deadlineNoDate_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        assertThrows(RogerInvalidInputException.class, () -> parser.parse("deadline homework"));
    }

    @Timeout(1)
    @Test
    public void parse_markNothing_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        assertThrows(RogerInvalidInputException.class, () -> parser.parse("mark q"));
    }

    @Timeout(1)
    @Test
    public void parse_event_returnAddEventCommand() throws Exception {
        Parser parser = new Parser();
        assertTrue(parser.parse("event happy /at 2022-10-04") instanceof AddEventCommand);
    }
}
