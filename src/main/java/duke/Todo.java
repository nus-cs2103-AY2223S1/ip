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
    public String formatFileText() {
        String s = String.format("T | %s | %s\n", super.getStatusIcon(), this.description);
        return s;
    }

    /**
     * Formats and outputs the string representation of Todo Task viable for writing
     * into text file.
     * @return formatted txt file string representation of a Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
