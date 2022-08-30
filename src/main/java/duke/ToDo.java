package duke;

/**
 * A ToDo class containing a ToDo description.
 * Inherits from the Task class.
 *
 */
public class   ToDo extends Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a ToDo object.
     */
    public ToDo(String description) {
        super(description);
    }


    /**
     * Returns the ToDO object in string.
     *
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
