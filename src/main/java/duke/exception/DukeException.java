package duke.exception;

/**
 * Represents an exception thrown when user enters invalid/ not properly formatted commands,
 * or when other errors occurs during TaskDive chatbot execution.
 */
public class DukeException extends Exception {

    /**
     * Creates DukeException with ErrorMessage and tasktype (if it is required to fill ErrorMessage).
     */
    public DukeException(ErrorMessage error, String taskType) {
        super(error.getName(taskType));
    }
}
