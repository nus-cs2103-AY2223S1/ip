package seedu.duke;

public class ToDo extends Task {

    /**
     * Represents a ToDo task.
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
