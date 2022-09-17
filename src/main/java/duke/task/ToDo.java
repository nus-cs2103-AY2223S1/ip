package duke.task;

/**
 * ToDo class represents the todo task created by the user.
 */
public class ToDo extends Task {

    /**
     * Constructor of the ToDo class.
     * Sets the description of the ToDo task to
     * the local variable.
     *
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string formatting for the Todo task
     * to be stored in the txt file.
     *
     * @return The string formatting of the todo task.
     */
    @Override
    public String stringFormatting() {
        return "T" + super.stringFormatting();
    }
}
