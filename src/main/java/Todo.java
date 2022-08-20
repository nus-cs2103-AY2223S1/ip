public class Todo extends Task {

    /**
     * Creates a todo task
     * @param description description of the todo task
     */
    public Todo(String description) {
        super(description, "T");
    }

    /**
     * @return String representation of the todo task
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString();
    }
}
