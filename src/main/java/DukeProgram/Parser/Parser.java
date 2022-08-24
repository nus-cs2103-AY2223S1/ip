package DukeProgram.Parser;

import DukeProgram.Commands.Command;
import DukeProgram.Commands.ExitCommand;
import DukeProgram.Commands.FactoryResetCommand;
import DukeProgram.Commands.UseTaskListCommand;
import Exceptions.InvalidCommandException;

public class Parser {
    public static Command parse(String commandString) throws InvalidCommandException {
        switch (commandString) {
        case "tasks":
            return new UseTaskListCommand();

        case "factory reset":
            return new FactoryResetCommand();

        case "exit":
            return new ExitCommand();

        default:
            throw new InvalidCommandException(commandString);
        }
    }
}
