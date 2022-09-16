package duke.model;

/**
 * A Task with only the activity specified.
 */
public class ToDo extends Task {

    /**
     * A constructor for a ToDo.
     *
     * @param description The details of the activity.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string of a ToDo to be stored in the storage.
     *
     * @return A formatted string for a ToDo storage.
     */
    @Override
    public String toStorage() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + description + "\n";
    }

    /**
     * Returns a string representation of a ToDo.
     *
     * @return A string representing a ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
