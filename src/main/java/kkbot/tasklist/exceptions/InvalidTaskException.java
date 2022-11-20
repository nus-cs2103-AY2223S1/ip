package kkbot.tasklist.exceptions;

import kkbot.exceptions.KKBotException;

/**
 * An exception thrown when user tries to access a task that does not exist.
 *
 * @author AkkFiros
 */
public class InvalidTaskException extends KKBotException {
    /**
     * Constructor for an InvalidTaskException.
     */
    public InvalidTaskException() {
        super("This task doesn't exist!");
    }
}
