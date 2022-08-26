package exceptions;

import task.TaskType;

/**
 * Used when the description field is empty
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructs an exception that indicates that the description of a task type cannot be empty
     *
     * @param taskType The specified task type.
     */
    public EmptyDescriptionException(TaskType taskType) {
        super("     ☹ OOPS!!! The description for " + taskType.value + " cannot be empty.");
    }
}
