package duke.task;

/**
 * Encapsulates a to-do task stored by Apollo.
 *
 * @author Kartikeya
 */
public class ToDo extends DukeTask {
    private final String description;

    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    public String getStorageString() {
        return "T >> " + (this.isDone() ? "1" : "0") + " >> "
                + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
