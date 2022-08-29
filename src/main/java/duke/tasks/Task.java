package duke.tasks;

import duke.exceptions.DukeException;

/**
 * This abstract class encapsulates the task that the user is trying to store on Duke.
 */
public abstract class Task {
    /**
     * This enum class encapsulates the different types of tasks.
     */
    public enum TaskType {
        TODO("T"),
        DEADLINE("D"),
        EVENT("E");

        /** String representation of the type of task. */
        public final String value;

        /**
         * Constructs a TaskType object of the corresponding string representation.
         *
         * @param value String representation of the task.
         */
        TaskType(String value) {
            this.value = value;
        }

        /**
         * Returns a TaskType based on the string representation of the type of task.
         *
         * @param str The string representation of the type of task that wants to be returned.
         * @return A TaskType with the same string representation as the input string.
         * @throws DukeException
         */
        public static TaskType parseTaskType(String str) throws DukeException {
            switch (str) {
            case "T":
                return TODO;
            case "D":
                return DEADLINE;
            case "E":
                return EVENT;
            default:
                throw new DukeException("Exception: Unknown task type.");
            }
        }
    }

    /** Description of the task. */
    private String description;
    /** Type of task. */
    private TaskType taskType;
    /** Completion status of the task. */
    private boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description Description of the task.
     * @param taskType Type of task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
        isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description string of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the TaskType of the task.
     *
     * @return TaskType of the class.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Checks the completion status of the task.
     *
     * @return Boolean representing if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the string representation of the type of task.
     *
     * @return String representation of the type of task.
     */
    public String getTaskIcon() {
        return taskType.value;
    }

    /**
     * Gets the string representation of the completion status of the task.
     *
     * @return String representation of the completion status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "x" : " ";
    }

    /**
     * Sets the completion status of the task to the given boolean.
     *
     * @param isDone The completion status to set the task to.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
