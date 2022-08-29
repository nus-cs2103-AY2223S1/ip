package duke;

/**
 * A Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveData() {
        return "T" + " | " + super.toSaveData();
    }
}
