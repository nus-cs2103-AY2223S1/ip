package candice.exception;

import candice.command.CommandType;

/**
 * Abstraction for exceptions that are thrown when only command types with no further description is inputted.
 */
public class EmptyCommandDescriptionException extends Exception {
    /**
     * Constructor for an exception thrown when commands lack descriptions.
     *
     * @param taskType The type of command inputted.
     */
    public EmptyCommandDescriptionException(CommandType taskType) {
        super("You can't just say " + taskType + " without any description.");
    }
}
