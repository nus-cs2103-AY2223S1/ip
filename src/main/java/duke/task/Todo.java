package duke.task;

/**
 * Encapsulation of a to-do.
 *
 * @author Sun Ruoxin
 */
public class Todo extends Task {
    /**
     * Class constructor.
     *
     * @param description the description of the to-do
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Class constructor with specified status.
     *
     * @param description the description of the to-do
     * @param isDone the status of the to-do
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the type of the task.
     *
     * @return "T" representing to-do
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns the string representation of the to-do.
     *
     * @return the string representation of the to-do
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
