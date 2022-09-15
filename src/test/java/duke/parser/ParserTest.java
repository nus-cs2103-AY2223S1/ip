package duke.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.RemindCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parseTest_bye() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
    }

    @Test
    public void parseTest_list() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parseTest_remind() throws DukeException {
        assertTrue(Parser.parse("remind") instanceof RemindCommand);
    }
}