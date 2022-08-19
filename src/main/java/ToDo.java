/**
 * The ToDo class represents a task
 * the user specified that needs to be done.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     * @param description description for the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overriding method of toString() for ToDo.
     * @return the string representing ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
