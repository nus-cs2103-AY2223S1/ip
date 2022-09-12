package sally.task;

/**
 * ToDo class to represent new Todo task
 *
 * @author liviamil
 */

public class ToDo extends Task {
    /**
     * Constructor for todo tasks
     *
     * @param description description for the todo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets the output string for save to file
     *
     * @return output string for save to file
     */
    public String getOutput() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
