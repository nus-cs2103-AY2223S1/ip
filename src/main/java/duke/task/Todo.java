package duke.task;

/**
 * Represents a task that needs to be done
 */
public class Todo extends Task {

    /**
     * A constructor for the Todo class
     *
     * @param description Description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
