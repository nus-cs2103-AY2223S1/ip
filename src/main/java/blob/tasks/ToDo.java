package blob.tasks;

import blob.exception.InvalidPriorityException;

/**
 * The ToDo class encapsulates a type of task to be done.
 * A ToDo task is a task without any date/time attached to it.
 */
public class ToDo extends Task {
    public ToDo(String description, String priority) throws InvalidPriorityException {
        super(description, TaskType.TODO, priority);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Returns the formatted ToDo task details to be stored in text file.
     *
     * @return Formatted ToDo task details to be stored in text file
     */
    @Override
    public String toFileString() {
        return super.toFileString();
    }
}
