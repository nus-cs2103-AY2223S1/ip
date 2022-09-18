package KKBot.parser.exceptions;

import KKBot.exceptions.KKBotException;

/**
 * An exception thrown when user inputs an invalid argument.
 *
 * @author AkkFiros
 */
public class InvalidArgumentException extends KKBotException {
    /**
     * Constructor for an InvalidTArgumentException.
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
