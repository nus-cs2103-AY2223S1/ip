package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parse_notACommand_exceptionThrown() {
        try {
            Parser.parseCommand("hello");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command: Please try again.",
                    e.getMessage());
        }
    }

    @Test
    public void parse_bye_success() throws DukeException {
        assertTrue(Parser.parseCommand("bye") instanceof ByeCommand);
    }

    @Test
    public void parse_list_success() throws DukeException {
        assertTrue(Parser.parseCommand("list") instanceof ListCommand);
    }
}
