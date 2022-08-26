package seedu.duke;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo task.
     * @param description description of ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String output = String.format("[T][%s] %s", this.getStatusIcon(), this.description);
        return output;
    }
}
