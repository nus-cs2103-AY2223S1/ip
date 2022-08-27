package dukeprogram.parser;

import dukeprogram.commands.Command;
import exceptions.InvalidCommandException;

/**
 * Parser will parse a command string in any given command context.
 */
public class Parser {

    /**
     * Parses a command string within the given context.
     * @param contextCommand the current context for the command
     * @param commandString the string to be parsed
     * @return a new command that is valid within the current context
     * @throws InvalidCommandException if no such command is possible in this context
     */
    public static Command parse(Command contextCommand, String commandString)
            throws InvalidCommandException {
        return contextCommand.parse(commandString);
    }
}
