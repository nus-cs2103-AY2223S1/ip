/**
 * ToDos are task objects that only have a description.
 *
 * @author AkkFiros
 */
public class ToDo extends Task {
    /**
     * Constructor for a ToDo task
     * @param description description of a task that the user inputs
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * toString method for a ToDo task
     * @return string representation of a ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
