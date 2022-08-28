package duke.task;

/**
 * Represents a Task with no specified date and time.
 */
public class Todo extends Task{
    /**
     * Constructs a Todo object with a description.
     *
     * @param description description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object with a description and done.
     *
     * @param description description of the task.
     * @param done task done or not.
     */
    public Todo(String description, boolean done) {
        super(description, done);
    }

    /**
     * Returns a String representation of Todo object.
     *
     * @return String representation of Todo object.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
