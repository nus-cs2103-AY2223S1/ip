package DukeProgram.Parser;

import DukeProgram.Commands.Command;
import Exceptions.InvalidCommandException;

/**
 * Parser parses any given command with the currently executing context
 */
public class Parser {

    /**
     * Parses the given command in context.
     * Functionally equivalent to contextCommand.parse(commandString);
     * @param contextCommand the Command to parse from
     * @param commandString the command string to be parsed
     * @return a resulting command due to the parse instruction
     * @throws InvalidCommandException if the command instruction could not be interpreted
     */
    public static Command parse(Command contextCommand, String commandString)
            throws InvalidCommandException {
        return contextCommand.parse(commandString);
    }
}
