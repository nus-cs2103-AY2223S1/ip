package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.ListCommand;

public class ParserTest {
    @Test
    public void parseByeCommand_validByeCommand_success() throws DukeException {
        Command currCommand = Parser.parse("bye", null);
        Assertions.assertTrue(currCommand instanceof ByeCommand);
    }

    @Test
    public void parseListCommand_validListCommand_success() throws DukeException {
        Command currCommand = Parser.parse("list", null);
        Assertions.assertTrue(currCommand instanceof ListCommand);
    }

    @Test
    public void parseDeadlineCommand_validDeadlineCommand_success() throws DukeException {
        Command currCommand = Parser
                .parse("deadline do homework /by 2022-08-25", null);
        Assertions.assertTrue(currCommand instanceof DeadlineCommand);
    }

    @Test
    public void parseDeadlineCommand_noDeadline_exceptionThrown() {
        Assertions.assertThrows(DukeException.class, () -> Parser
                .parse("deadline do homework", null));
    }
}
