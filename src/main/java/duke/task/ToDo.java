package duke.task;

/**
 * Represents a task to be conducted which is one type of task.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo instance with a description.
     *
     * @param description Description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
