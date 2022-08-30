package duke;

/**
 * Represents an Todo task, with no date or time.
 */
public class Todo extends Task {
    /**
     * Initializes a new Todo object which is a subclass of Task.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initializes a new Todo object which is a subclass of Task, with the option to specify if the
     * task is done or not.
     *
     * @param description Description of the Todo task.
     * @param status Completion state of the deadline task.
     */
    public Todo(String description, int status) {
        this(description);
        isDone = status == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String parseToSaveData() {
        return "T" + "|" + super.parseToSaveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
