package duke;

/**
 * Todo task
 */
public class Todo extends Task {
    /**
     * Initialises a Todo object
     * @param description Description of Todo Task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return String format to store the task in storage
     */
    @Override
    public String toFileString() {
        return String.format("T | %s | %s", getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            return this.description.equals(((Todo) o).description);
        }
        return false;
    }
}
