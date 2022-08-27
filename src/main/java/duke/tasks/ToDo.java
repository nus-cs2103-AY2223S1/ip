package duke.tasks;

/**
 * Describes the ToDo class.
 */
public class ToDo extends Task {

    /**
     * Constructor when taking user input.
     * @param description description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor when taking user input.
     * @param isMarked boolean value of whether the task is marked.
     * @param description description of the ToDo.
     */
    public ToDo(boolean isMarked, String description) {
        super(isMarked, description);
    }

    @Override
    public String dbRepresentation() {
        return String.join("|", "T", Boolean.toString(isMarked), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
