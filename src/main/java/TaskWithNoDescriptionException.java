package duke;

/**
 * Represents an exception about task with no description.
 */
public class TaskWithNoDescriptionException extends Exception {

    /**
     * Construct a TaskWithNoDescriptionException with specific message.
     */
    public TaskWithNoDescriptionException(String message) {
        super(message);
    }

}
