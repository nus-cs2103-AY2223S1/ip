package roger;

import org.junit.jupiter.api.Test;
import roger.commands.AddEventCommand;
import roger.exceptions.RogerInvalidInputException;
import roger.ui.Parser;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    @Test
    public void parse_deadlineNoDate_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        assertThrows(RogerInvalidInputException.class, () -> parser.parse("deadline homework"));
    }

    @Test
    public void parse_markNothing_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        assertThrows(RogerInvalidInputException.class, () -> parser.parse("mark q"));
    }

    @Test
    public void parse_event_returnAddEventCommand() throws Exception {
        Parser parser = new Parser();
        assertTrue(parser.parse("event happy /at 2022-10-04") instanceof AddEventCommand);
    }
}
