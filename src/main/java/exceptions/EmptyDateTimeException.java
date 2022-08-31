package exceptions;

import task.TaskType;

/**
 * Used when datetime field is empty,
 */
public class EmptyDateTimeException extends DukeException {
    /**
     * Constructs an exception that indicates that the datetime field cannot be empty for the corresponding
     * task type.
     *
     * @param taskType The specified task type.
     */
    public EmptyDateTimeException(TaskType taskType) {
        super("😅 OOPS!!! The datetime for " + taskType.getValue() + " cannot be empty.");
    }
}
