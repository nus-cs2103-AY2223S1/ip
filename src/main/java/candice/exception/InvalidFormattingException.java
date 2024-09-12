package candice.exception;

import candice.command.CommandType;

/**
 * Abstraction for exceptions that are thrown when the format of the command inputted is not valid.
 */
public class InvalidFormattingException extends Exception {
    /**
     * Constructor for an exception thrown when the command inputted does not have valid formatting.
     *
     * @param taskType The type of command inputted.
     */
    public InvalidFormattingException(CommandType taskType) {
        super("Wrong formatting for a " + taskType + " bro.");
    }
}
