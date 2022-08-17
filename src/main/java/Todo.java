public class Todo extends Task {
    /**
     * The class constructor, which utilizes the superclass
     * constructor.
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString method of the superclass to add
     * the additional [T] tag.
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
