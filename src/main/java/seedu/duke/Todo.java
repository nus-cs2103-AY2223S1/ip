package seedu.duke;

/**
 * A task object which contains the description.
 */
public class Todo extends Task {

    /**
     * A constructor for Todo.
     *
     * @param description the desciption of the task.
     */
    public Todo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
