package duke.task;

import duke.exception.DukeException;

/**
 * Represents the type of task.
 */
public enum TaskType {

    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    private final String task;

    /**
     * Constructs a new TaskType.
     *
     * @param task Type of task.
     */
    TaskType(String task) {
        this.task = task;
    }

    /**
     * Returns a TaskType based on the given task input.
     *
     * @param task String representation of the task input.
     * @return TaskType based on the task input.
     * @throws DukeException If the task input given does not have a representing TaskType.
     */
    public static TaskType parse(String task) throws DukeException {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.task.equals(task)) {
                return taskType;
            }
        }
        throw new DukeException("Sorry, there is no such task type!");
    }
}
