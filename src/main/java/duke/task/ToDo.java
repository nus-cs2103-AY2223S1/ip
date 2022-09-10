package duke.task;

/**
 * Encapsulates a to-do task stored by Artemis.
 *
 * @author Kartikeya
 */
public class ToDo extends DukeTask {

    /**
     * Constructor for a ToDo task.
     *
     * @param description description of todo task
     */
    public ToDo(String description) {
        super(description);
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
