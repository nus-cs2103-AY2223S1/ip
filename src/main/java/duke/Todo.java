package duke;

/**
 * ATodo class which is a subclass of Task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo Class.
     *
     * @param description Description of the todo.
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

    /**
     * @inheritDoc
     */
    @Override
    public String toSavedString() {
        return "T | " + (this.isDone ? "1 | " : "0 | ") + this.description;
    }
}