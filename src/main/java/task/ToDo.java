package task;

/**
 * The ToDo class encapsulates a type of task to be done.
 * A ToDo task is a task without any date/time attached to it.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", "T", super.toString());
    }
}
