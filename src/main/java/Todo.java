/**
 * A subclass of the task class.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo instance.
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString method in the parent class.
     * @return A string representing the current todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
