public class Todo extends Task {

    /**
     * Creates a todo task
     * @param description description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @return String representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
