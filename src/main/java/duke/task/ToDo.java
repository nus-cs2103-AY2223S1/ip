package duke.task;

/**
 * Represents a ToDo Task Object.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo object from description.
     * @param description Description for the ToDo object.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo object from description and isDone status.
     * @param description Description for the ToDo object.
     * @param isDone IsDone status for the ToDo object.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets the string representation of the ToDo object.
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * String representation of the ToDo object to be saved to and loaded from.
     * @return String representation of the ToDo object to be saved.
     */
    @Override
    public String toSave() {
        return "T" + Task.SAVE_SEPARATOR + this.getIsDoneString() + Task.SAVE_SEPARATOR + this.getDescription();
    }
}
