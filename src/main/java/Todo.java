public class Todo extends Task {
    /**
     * Constructor for a to-do instance.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of the to-do.
     *
     * @return String representing this task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
