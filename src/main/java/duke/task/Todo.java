package duke.task;

/**
 * Task child class that specifies a Todo task.
 */
public class Todo extends Task {

    protected String by;

    /**
     * Initialises Todo class.
     *
     * @param description Description for Todo task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
