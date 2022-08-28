package duke.models;

/**
 * Represents a Todo.
 *
 * @author Zhu Yuanxi
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object.
     *
     * @param description The description of the deadline.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object.
     *
     * @param description The description of the deadline task.
     * @param isDone The status of the deadline task.
     */
    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    /**
     * Formats the string representation of the todo object for save.
     *
     * @return The string representation of a todo object for save.
     */
    public String formatForSave() {
        return "T | " + super.formatForSave();
    }

    /**
     * Formats the string representation of the todo object for display.
     *
     * @return The string representation of a todo object for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
