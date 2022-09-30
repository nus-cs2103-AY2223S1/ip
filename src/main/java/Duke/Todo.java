package duke;

/**
 * Todo object class.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object with the description.
     *
     * @param description Details of the Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Represents a Todo object.
     *
     * @return String representation of Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
