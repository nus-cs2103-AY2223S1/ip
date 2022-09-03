package candice.exception;

import candice.command.CommandType;

/**
 * Abstraction for exceptions that are thrown when only command types with no description of the task are inputted.
 */
public class EmptyTaskNameException extends Exception {
    /**
     * Constructor for an exception thrown when commands lack task descriptions.
     *
     * @param taskType The type of command inputted.
     */
    public EmptyTaskNameException(CommandType taskType) {
        super("You can't just say " + taskType + " without any description.");
    }
}
