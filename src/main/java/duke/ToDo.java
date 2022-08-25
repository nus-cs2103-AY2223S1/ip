package duke;

/**
 * A type of task
 *
 * @author Sean Lam
 */
public class ToDo extends Task {
    protected String type = "[T]";

    /**
     * Constructor of ToDo
     *
     * @param description Description of task
     */
    ToDo(String description) {
        super(description);
    }

    /**
     * Returns string representation of Todo task to be stored in dukeHistory
     *
     * @return Todo task string
     */
    @Override
    public String toString() {
        return type + super.toString();
    }

    /**
     * Returns string representation of ToDO task to be stored in dukeHistory
     *
     * @return Todo task string
     */
    @Override
    public String toFile() {
        String isDone = "0";
        if (super.isDone) {
            isDone = "1";
        }
        return String.format("T|%s|%s\n", isDone, super.description);
    }
}
