public class Todo extends Task {
    /**
     * Constructor for this object.
     * @param description the description of the task to do
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string used to save the task.
     * @return a string used to save the task
     */
    @Override
    public String saveString() {
        return String.format("T | %s", super.saveString());
    }

    /**
     * Returns a string representation of this object.
     * @return a string representing this object
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
