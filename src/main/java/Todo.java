public class Todo extends Task {
    protected String by;

    /**
     * Constructor for the to-do type of task.
     *
     * @param description description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * toString method of a to-do task.
     *
     * @return the string representation of a to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
