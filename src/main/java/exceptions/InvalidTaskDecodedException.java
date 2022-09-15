package exceptions;

import task.TaskType;

/**
 * Used when the task data being decoded is invalid.
 */
public class InvalidTaskDecodedException extends DukeException {

    /**
     * Constructs an exception that indicates that the task data being read from the storage file does not adhere to
     * any task type.
     *
     * @param taskData The specified task data.
     */
    public InvalidTaskDecodedException(String taskData) {
        super("🥚 OOPS!!! Invalid format for \n" + taskData);
    }

    /**
     * Constructs an exception that indicates that the task data being read from the storage file does not adhere to
     * the format of the specified task type.
     *
     * @param taskType The specified task type.
     * @param taskData The specified task data.
     */
    public InvalidTaskDecodedException(TaskType taskType, String taskData) {
        super("🥚 OOPS!!! Invalid format for " + taskType.getValue() + "\n" + taskData);
    }
}
