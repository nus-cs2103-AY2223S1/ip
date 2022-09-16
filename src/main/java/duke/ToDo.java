package duke;

/**
 * Represents a ToDo, which is a Task.
 */
public class ToDo extends Task {
    /**
     * Constructor of ToDo with description.
     *
     * @param description Description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
        type = 'T';
    }

    /**
     * Constructor of ToDo with description and boolean to set the ToDo as done or not done.
     *
     * @param description Description of the ToDo.
     * @param isDone Boolean to set the ToDo as done or not done.
     */
    public ToDo(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }
}
