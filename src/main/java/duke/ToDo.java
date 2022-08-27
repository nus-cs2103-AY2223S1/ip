package duke;

/**
 * The ToDo class encapsulates a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Instantiates a ToDo object.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Instantiates a ToDo object.
     *
     * @param description Description of the task.
     * @param isDone Flag to indicate if task is done or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String fileStatus() {
        return "T | " + super.fileStatus();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
