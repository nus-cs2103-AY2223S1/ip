package iana.command;

/**
 * Enum for actions Iana can do.
 */
public enum Actions {

    /**
     * Exit program.
     */
    bye,

    /**
     * List out current tasks.
     */
    list,

    /**
     * Delete a task.
     */
    delete,

    /**
     * Mark a task as completed.
     */
    mark,

    /**
     * Mark a task as not completed.
     */
    unmark,

    /**
     * Add a new todo task.
     */
    todo,

    /**
     * Add a new event task.
     */
    event,

    /**
     * Add a new deadline task.
     */
    deadline
}
