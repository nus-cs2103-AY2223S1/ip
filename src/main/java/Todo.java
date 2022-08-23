public class Todo extends Task {
    /**
     * Constructs a to-do task.
     *
     * @param description Description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Shows the to-do task description.
     *
     * @return String with the to-do task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}