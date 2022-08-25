package duke.task;

import duke.task.Task;

/**
 * ToDo is a user set task.
 *
 * @author totsukatomofumi
 */
public class ToDo extends Task {
    /**
     * Constructs a todo.
     *
     * @param description the description of the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return a string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the event that conforms to how storage
     * parses from the task history file.
     *
     * @return the parsable string representation of the todo.
     */
    @Override
    public String toData() {
        return "T" + super.toData();
    }
}
