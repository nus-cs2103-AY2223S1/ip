package candice.exception;

import candice.command.CommandType;

/**
 * Abstraction for exceptions that are thrown when no date for a task is inputted, specifically for deadlines and
 * events.
 */
public class EmptyTimingException extends Exception {
    /**
     * Constructor for an exception thrown when the deadline or event command inputted lacks a date.
     *
     * @param taskType The type of command inputted, specifically deadlines or events.
     */
    public EmptyTimingException(CommandType taskType) {
        super("You have not set a timing for your " + taskType + " buddy.");
        assert taskType == CommandType.DEADLINE || taskType == CommandType.EVENT : "Invalid command type for " +
                "EmptyTimingException";
    }
}
