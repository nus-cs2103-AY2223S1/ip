package duke.types;

/**
 * Todo represents a todo for a task.
 *
 * @author Aaron Tan
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param description Description of task.
     * @param isDone If task is done or not done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Generates a String to be saved.
     *
     * @return String in the format of T | isDone | description.
     */
    public String saveString() {
        return String.format("T | %s | %s\n", super.isDone ? "1" : "0",
                super.getDescription());
    }

    /**
     * Generates a String for representation during list.
     *
     * @return String in the format of [T][isDone] description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
