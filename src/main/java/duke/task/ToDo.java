package duke.task;

/**
 * Represents a task without any date attached to it.
 */
public class ToDo extends Task {
    private static final String TODO_REP = "T";

    /**
     * Constructs a new ToDo.
     *
     * @param message Description of the ToDo.
     */
    public ToDo(String message) {
        super(message);
    }

    /**
     * Returns a String representation of the ToDo.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[" + TODO_REP + "]" + super.toString();
    }

    /**
     * Returns a String representation of the ToDo for storage.
     *
     * @return String representation of the ToDo for storage.
     */
    @Override
    public String toStorage() {
        return TODO_REP + super.toStorage();
    }
}
