package duke;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    Parser parser = new Parser();

    @Test
    public void parse_byeCommand_byeCommandInstanceReturned() throws DukeException {
        assertTrue(parser.parse("bye") instanceof ByeCommand);
    }

    @Test
    public void parse_listCommand_listCommandInstanceReturned() throws DukeException {
        assertTrue(parser.parse("list") instanceof ListCommand);
    }
}
