package iana.tasks;

/**
 * Enum for the different types of tasks recognised.
 */
public enum TaskType {

    /**
     * A todo task.
     */
    todo,

    /**
     * An event task with time description.
     */
    event,

    /**
     * A deadline task with time description.
     */
    deadline
}