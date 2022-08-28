package unc.task;

/**
 * A type of task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructor for new Todos.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for saved Todos.
     *
     * @param description Description of task.
     * @param done Whether the task was saved as done.
     */
    public Todo(String description, String done) {
        super(description, done == "true");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageString() {
        return "T" + "///" + this.description + "///" + " " + "///" + this.isDone;
    }
}