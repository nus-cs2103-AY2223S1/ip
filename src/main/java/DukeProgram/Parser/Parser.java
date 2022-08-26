package DukeProgram.Parser;

import DukeProgram.Commands.*;
import DukeProgram.Commands.TaskList.SelectTaskListsCommand;
import Exceptions.InvalidCommandException;

public class Parser {
    public static Command parse(Command contextCommand, String commandString)
            throws InvalidCommandException {
        return contextCommand.parse(commandString);
    }
}
