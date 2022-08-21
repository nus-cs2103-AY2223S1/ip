package duke.task;

/**
 * Class for a toDo task.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo with specified description.
     *
     * @param description The description of the toDo to be created.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo with specified description and completeness.
     *
     * @param description The description of the toDo to be created.
     * @param isDone If the toDo is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String stringify() {
        return String.format("%s | %s", "T", super.stringify());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
