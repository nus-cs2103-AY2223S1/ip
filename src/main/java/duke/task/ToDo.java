package duke.task;

/**
 * Encapsulates a to-do task stored by Apollo.
 *
 * @author Kartikeya
 */
public class ToDo extends DukeTask {
    private final String description;

    /**
     * Constructor for a ToDo task.
     *
     * @param description description of todo task
     */
    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public String getStorageString() {
        return "T >> " + (this.isDone() ? "1" : "0") + " >> "
                + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
