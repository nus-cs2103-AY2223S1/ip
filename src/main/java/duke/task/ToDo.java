package duke.task;

/**
 * Represents an ToDo task.
 * @author Lim Ai Lin
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo task object.
     * @param description The name of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
