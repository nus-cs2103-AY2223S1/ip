package duke.parser;

import duke.command.ExitCommand;
import duke.exception.DukeException;

public class ParserStub extends Parser {
    public static ExitCommand parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }
        return null;
    }
}
