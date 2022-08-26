package dukeprogram.parser;

import dukeprogram.commands.Command;
import exceptions.InvalidCommandException;

public class Parser {
    public static Command parse(Command contextCommand, String commandString)
            throws InvalidCommandException {
        return contextCommand.parse(commandString);
    }
}
