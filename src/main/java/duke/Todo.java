package duke;

/**
 * Todo is a subclass of Task. There are no additional parameters
 * except the description of a task.
 */
public class Todo extends Task {

    /**
     * The class constructor, which utilizes the superclass
     * constructor.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of an Todo object formatted
     * for writing into text file.
     *
     * @return String of the Todo formatted to saved.
     */
    @Override
    public String formatFileText() {
        String s = String.format("T | %s | %s\n", super.getStatusIcon(), this.description);
        return s;
    }

    /**
     * Overrides the toString method of the superclass to add
     * the additional [T] tag.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
