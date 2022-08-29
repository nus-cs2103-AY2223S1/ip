package dukeprogram.parser;

import dukeprogram.command.Command;
import exceptions.InvalidCommandException;

/**
 * Parser will parse a command string in any given command context.
 */
public class Parser {

    /**
     * Parses a command string within the given context.
     * @param commandString the string to be parsed
     * @return a new command that is valid within the current context
     * @throws InvalidCommandException if no such command is possible in this context
     */
    public static String parse(String... commandString) throws InvalidCommandException {
        return parse(commandString);
    }
}
