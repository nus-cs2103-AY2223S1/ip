package justin.task;

import justin.task.Task;

/**
 * Represents a task that is to be done
 * in the future.
 * @author Justin Cheng.
 */
public class ToDo extends Task {
    /**
     * Constructor for the ToDo class.
     * @param description The description of the Task.
     * @param isDone The boolean value of whether the Task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the String representation of the ToDo task.
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "T | " + this.getStatusIcon() + " | " + this.getDescription();
    }
}
