package duke;

/**
 * The  TodoTask is a task that only takes in a description.
 */
public class TodoTask extends Task {

    /**
     * Constructor for a TodoTask object.
     *
     * @param description  Description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + tagsToString();
    }
}
