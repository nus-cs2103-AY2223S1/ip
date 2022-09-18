package KKBot.parser.exceptions;

import KKBot.exceptions.KKBotException;

/**
 * An exception thrown when user inputs an invalid command.
 *
 * @author AkkFiros
 */
public class InvalidCommandException extends KKBotException {
    /**
     * Constructor for an InvalidCommandException.
     */
    public InvalidCommandException() {
        super("Woops, your command doesn't make sense, please try again!");
    }
}
